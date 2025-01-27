package fr.benseddik.correctiontpspring.service;

import fr.benseddik.correctiontpspring.dto.BorrowingResponse;

import java.util.List;

public interface IBorrowingService {
    BorrowingResponse borrowBook(String userUuid, String bookUuid);

    void returnBook(String borrowingUuid);

    List<BorrowingResponse> getUserBorrowings(String userUuid);
}
