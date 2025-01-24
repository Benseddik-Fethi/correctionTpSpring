package fr.benseddik.correctiontpspring.service;

import fr.benseddik.correctiontpspring.domain.Book;
import fr.benseddik.correctiontpspring.domain.User;
import fr.benseddik.correctiontpspring.exception.BookNotFoundException;
import fr.benseddik.correctiontpspring.exception.UserNotFoundException;
import fr.benseddik.correctiontpspring.repository.IBookRepository;
import fr.benseddik.correctiontpspring.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final IBookRepository bookRepository;

    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByIsAvailableTrue();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Livre avec ID " + id + " non trouvé"));
    }

    public void deleteBook(Long id) {
        Book book = getBookById(id); // Vérifie si le livre existe avant de supprimer
        bookRepository.delete(book);
    }
}