package fr.benseddik.correctiontpspring.dto;

import java.io.Serializable;

/**
 * DTO for {@link fr.benseddik.correctiontpspring.domain.Book}
 */
public record BookRequest(
        String title,
        String author) implements Serializable {
}