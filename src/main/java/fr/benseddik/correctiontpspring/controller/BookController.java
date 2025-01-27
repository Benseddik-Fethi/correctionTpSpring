package fr.benseddik.correctiontpspring.controller;

import fr.benseddik.correctiontpspring.dto.BookRequest;
import fr.benseddik.correctiontpspring.dto.BookResponse;
import fr.benseddik.correctiontpspring.service.impl.BookServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/books")
public class BookController {

    private final BookServiceImpl bookServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<BookResponse> addBook(@RequestBody @Valid BookRequest bookRequest) {
        log.info("Adding new book: {}", bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookServiceImpl.saveBook(bookRequest));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        log.info("Getting all books");
        return ResponseEntity.ok(bookServiceImpl.getAllBooks());
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookResponse>> getAvailableBooks() {
        log.info("Get available books");
        return ResponseEntity.ok(bookServiceImpl.getAvailableBooks());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<BookResponse> getBookByUuid(@PathVariable String uuid) {
        log.info("Getting book by uuid: {}", uuid);
        return ResponseEntity.ok(bookServiceImpl.getBookByUuid(uuid));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deleteBook(@PathVariable String uuid) {
        log.info("Deleting book by uuid: {}", uuid);
        bookServiceImpl.deleteBook(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Livre avec uuid " + uuid + "a été supprimé");
    }
}
