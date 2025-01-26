package fr.benseddik.correctiontpspring.dto;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link fr.benseddik.correctiontpspring.domain.Book}
 */
public record BookResponse(UUID uuid, String title, String author, boolean isAvailable) implements Serializable {
}