package fr.benseddik.correctiontpspring.dto;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link fr.benseddik.correctiontpspring.domain.User}
 */
public record UserResponse(UUID uuid, String name, String email) implements Serializable {
}