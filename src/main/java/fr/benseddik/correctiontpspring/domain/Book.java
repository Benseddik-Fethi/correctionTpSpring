package fr.benseddik.correctiontpspring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_seq", allocationSize = 1, initialValue = 1000)
    @Column(nullable = false)
    private Long id;
    private UUID uuid;
    private String title;
    private String author;

    private boolean isAvailable;

}