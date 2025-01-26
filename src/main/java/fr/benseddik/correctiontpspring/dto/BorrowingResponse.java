package fr.benseddik.correctiontpspring.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link fr.benseddik.correctiontpspring.domain.Borrowing}
 */
public record BorrowingResponse(UUID uuid, UserResponse user, BookResponse book, LocalDateTime borrowDate,
                                LocalDateTime returnDate) implements Serializable {
}