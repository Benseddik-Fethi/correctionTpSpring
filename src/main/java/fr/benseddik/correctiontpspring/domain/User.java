package fr.benseddik.correctiontpspring.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "users")
public class User extends AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2456240789801539388L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1, initialValue = 1000)
    @Column(nullable = false)
    private Long id;
    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    @Column(unique = true)
    @Email(message = "Email is not valid")
    private String email;
}