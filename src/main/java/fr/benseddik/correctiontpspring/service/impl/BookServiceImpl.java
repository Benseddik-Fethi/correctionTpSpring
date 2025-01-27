package fr.benseddik.correctiontpspring.service.impl;

import fr.benseddik.correctiontpspring.domain.Book;
import fr.benseddik.correctiontpspring.dto.BookResponse;
import fr.benseddik.correctiontpspring.dto.BookRequest;
import fr.benseddik.correctiontpspring.dto.mapper.IBookMapper;
import fr.benseddik.correctiontpspring.error.exception.BookNotFoundException;
import fr.benseddik.correctiontpspring.repository.IBookRepository;
import fr.benseddik.correctiontpspring.service.IBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final IBookRepository bookRepository;
    private final IBookMapper bookMapper;


    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toBookResponse)
                .toList();
    }

    @Override
    public List<BookResponse> getAvailableBooks() {
        return bookRepository.findByIsAvailableTrue()
                .stream()
                .map(bookMapper::toBookResponse)
                .toList();
    }

    @Override
    public BookResponse getBookByUuid(String uuid) {
        return bookMapper
                .toBookResponse(bookRepository.findByUuid(UUID.fromString(uuid))
                        .orElseThrow(() -> new BookNotFoundException("Book not found")));
    }

    @Override
    public void deleteBook(String uuid) {
        BookResponse book = getBookByUuid(uuid);
        bookRepository.delete(bookMapper.bookResponseToBook(book));
    }

    @Override
    public BookResponse saveBook(BookRequest bookRequest){
       Book book = bookMapper.bookRequestToEntity(bookRequest);
       book.setAvailable(Boolean.TRUE);
        return bookMapper.toBookResponse(bookRepository.save(book));
    }
}
