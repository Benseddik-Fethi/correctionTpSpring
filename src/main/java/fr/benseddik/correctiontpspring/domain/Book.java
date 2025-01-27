package fr.benseddik.correctiontpspring.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Book extends AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 4156123851697498740L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_seq", allocationSize = 1, initialValue = 1000)
    @Column(nullable = false)
    private Long id;
    @NotNull(message = "Title is mandatory")
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotNull(message = "Author is mandatory")
    @NotBlank(message = "Author is mandatory")
    private String author;
    private boolean isAvailable;
}