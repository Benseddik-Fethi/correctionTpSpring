package fr.benseddik.correctiontpspring.service;

import fr.benseddik.correctiontpspring.domain.Book;
import fr.benseddik.correctiontpspring.domain.Borrowing;
import fr.benseddik.correctiontpspring.domain.User;
import fr.benseddik.correctiontpspring.exception.BookNotFoundException;
import fr.benseddik.correctiontpspring.exception.BookUnavailableException;
import fr.benseddik.correctiontpspring.exception.BorrowingLimitExceededException;
import fr.benseddik.correctiontpspring.exception.UserNotFoundException;
import fr.benseddik.correctiontpspring.repository.IBookRepository;
import fr.benseddik.correctiontpspring.repository.IBorrowingRepository;
import fr.benseddik.correctiontpspring.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowingService {

    private final IBorrowingRepository borrowingRepository;
    private final IUserRepository userRepository;
    private final IBookRepository bookRepository;


    public Borrowing borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur avec ID " + userId + " non trouvé"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Livre avec ID " + bookId + " non trouvé"));

        if (!book.isAvailable()) {
            throw new BookUnavailableException("Le livre '" + book.getTitle() + "' est déjà emprunté.");
        }

        List<Borrowing> activeBorrows = borrowingRepository.findByUserAndReturnDateIsNull(user);
        if (activeBorrows.size() >= 3) {
            throw new BorrowingLimitExceededException("L'utilisateur ne peut pas emprunter plus de 3 livres.");
        }

        book.setAvailable(false);
        Borrowing borrowing = new Borrowing();
        borrowing.setUser(user);
        borrowing.setBook(book);
        borrowing.setBorrowDate(LocalDate.now());

        return borrowingRepository.save(borrowing);
    }

    public void returnBook(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé"));

        borrowing.setReturnDate(LocalDate.now());
        borrowing.getBook().setAvailable(true);

        borrowingRepository.save(borrowing);
    }

    public List<Borrowing> getUserBorrowings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur avec ID " + userId + " non trouvé"));

        return borrowingRepository.findByUserAndReturnDateIsNull(user);
    }
}
