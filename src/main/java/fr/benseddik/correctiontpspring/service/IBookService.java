package fr.benseddik.correctiontpspring.service;

import fr.benseddik.correctiontpspring.dto.BookRequest;
import fr.benseddik.correctiontpspring.dto.BookResponse;

import java.util.List;

public interface IBookService {
    List<BookResponse> getAllBooks();

    List<BookResponse> getAvailableBooks();

    BookResponse getBookByUuid(String uuid);

    void deleteBook(String uuid);

    BookResponse saveBook(BookRequest bookRequest);
}
