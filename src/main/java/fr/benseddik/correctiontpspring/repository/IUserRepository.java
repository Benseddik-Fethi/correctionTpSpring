package fr.benseddik.correctiontpspring.repository;

import fr.benseddik.correctiontpspring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUuid(UUID uuid);
}
