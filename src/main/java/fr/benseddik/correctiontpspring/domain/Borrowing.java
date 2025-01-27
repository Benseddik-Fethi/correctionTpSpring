package fr.benseddik.correctiontpspring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Borrowing extends AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -3021034401818533310L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrow_seq")
    @SequenceGenerator(name = "borrow_seq", sequenceName = "borrow_seq", allocationSize = 1, initialValue = 1000)
    @Column( nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    
}