package fr.benseddik.correctiontpspring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrow_seq")
    @SequenceGenerator(name = "borrow_seq", sequenceName = "borrow_seq", allocationSize = 1, initialValue = 1000)
    @Column( nullable = false)
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;

}