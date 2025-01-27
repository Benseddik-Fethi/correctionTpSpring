package fr.benseddik.correctiontpspring.controller;

import fr.benseddik.correctiontpspring.dto.BorrowingResponse;
import fr.benseddik.correctiontpspring.service.impl.BorrowingServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/borrowings")
public class BorrowingController {

    private final BorrowingServiceImpl borrowingServiceImpl;


    @PostMapping("/borrow")
    public ResponseEntity<BorrowingResponse> borrowBook(@RequestParam String userUuid, @RequestParam String bookUuid) {
        log.info("Borrowing book with UUID: {} and User UUID {} ",bookUuid, userUuid);
        return ResponseEntity.ok(borrowingServiceImpl.borrowBook(userUuid, bookUuid));
    }

    @PostMapping("/return/{borrowingUuid}")
    public ResponseEntity<String> returnBook(@PathVariable String borrowingUuid) {
        log.info("Return book with UUID {}", borrowingUuid);
        borrowingServiceImpl.returnBook(borrowingUuid);
        return ResponseEntity.ok("Livre rendu avec succès !");
    }

    @GetMapping("/user/{userUuid}")
    public ResponseEntity<List<BorrowingResponse>> getUserBorrowings(@PathVariable String userUuid) {
        log.info("getUserBorrowings {}", userUuid);
        return ResponseEntity.ok(borrowingServiceImpl.getUserBorrowings(userUuid));
    }
}
