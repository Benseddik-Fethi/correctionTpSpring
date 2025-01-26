package fr.benseddik.correctiontpspring.dto;

import java.io.Serializable;

/**
 * DTO for {@link fr.benseddik.correctiontpspring.domain.User}
 */
public record UserRequest(String name, String email) implements Serializable {
}