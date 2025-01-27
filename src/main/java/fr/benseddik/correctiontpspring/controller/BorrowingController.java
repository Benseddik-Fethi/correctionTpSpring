package fr.benseddik.correctiontpspring.controller;

import fr.benseddik.correctiontpspring.domain.Borrowing;
import fr.benseddik.correctiontpspring.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowings")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;


    @PostMapping("/borrow")
    public ResponseEntity<Borrowing> borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return ResponseEntity.ok(borrowingService.borrowBook(userId, bookId));
    }

    @PostMapping("/return/{borrowingId}")
    public ResponseEntity<String> returnBook(@PathVariable Long borrowingId) {
        borrowingService.returnBook(borrowingId);
        return ResponseEntity.ok("Livre rendu avec succès !");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Borrowing>> getUserBorrowings(@PathVariable Long userId) {
        return ResponseEntity.ok(borrowingService.getUserBorrowings(userId));
    }
}
