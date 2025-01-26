package fr.benseddik.correctiontpspring.service;

import fr.benseddik.correctiontpspring.domain.Book;
import fr.benseddik.correctiontpspring.domain.Borrowing;
import fr.benseddik.correctiontpspring.domain.User;
import fr.benseddik.correctiontpspring.dto.BorrowingResponse;
import fr.benseddik.correctiontpspring.dto.mapper.IBorrowingMapper;
import fr.benseddik.correctiontpspring.exception.BookNotFoundException;
import fr.benseddik.correctiontpspring.exception.BookUnavailableException;
import fr.benseddik.correctiontpspring.exception.BorrowingLimitExceededException;
import fr.benseddik.correctiontpspring.exception.UserNotFoundException;
import fr.benseddik.correctiontpspring.repository.IBookRepository;
import fr.benseddik.correctiontpspring.repository.IBorrowingRepository;
import fr.benseddik.correctiontpspring.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BorrowingService {

    private final IBorrowingRepository borrowingRepository;
    private final IUserRepository userRepository;
    private final IBookRepository bookRepository;
    private final IBorrowingMapper borrowingMapper;

    public BorrowingResponse borrowBook(String userUuid, String bookUuid) {
        log.info("Borrowing book using UUID: {} and book UUID: {}", userUuid, bookUuid);
        User user = userRepository.findByUuid(UUID.fromString(userUuid))
                .orElseThrow(() -> new UserNotFoundException(userUuid));
        Book book = bookRepository.findByUuid(UUID.fromString(bookUuid))
                .orElseThrow(() -> new BookNotFoundException(bookUuid));
        if (!book.isAvailable()) {
            throw new BookUnavailableException("Le livre '" + book.getTitle() + "' est déjà emprunté.");
        }

        List<Borrowing> activeBorrows = borrowingRepository.findByUserAndReturnDateIsNull(user);
        if (activeBorrows.size() >= 3) {
            throw new BorrowingLimitExceededException("L'utilisateur ne peut pas emprunter plus de 3 livres.");
        }
        book.setAvailable(false);
        Borrowing borrowing = Borrowing.builder()
                .user(user)
                .book(book)
                .returnDate(LocalDateTime.now())
                .build();
        return borrowingMapper.entityToborrowingResponse(borrowingRepository.save(borrowing));
    }

    public void returnBook(String borrowingUuid) {
        log.info("Return book using UUID: {}", borrowingUuid);
        Borrowing borrowing = borrowingRepository.findById(Long.valueOf(borrowingUuid))
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé"));
        borrowing.setReturnDate(LocalDateTime.now());
        borrowing.getBook().setAvailable(true);
        borrowingRepository.save(borrowing);
    }

    public List<BorrowingResponse> getUserBorrowings(String userUuid) {
        User user = userRepository.findByUuid(UUID.fromString(userUuid))
                .orElseThrow(() -> new UserNotFoundException("Utilisateur avec UUID " + userUuid + " non trouvé"));
        return borrowingRepository
                .findByUserAndReturnDateIsNull(user)
                .stream()
                .map(borrowingMapper::entityToborrowingResponse)
                .toList();
    }
}
