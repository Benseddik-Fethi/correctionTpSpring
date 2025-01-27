package fr.benseddik.correctiontpspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link fr.benseddik.correctiontpspring.domain.Book}
 */
public record BookRequest(
        String title,
        String author) implements Serializable {
}