package fr.benseddik.correctiontpspring.repository;

import fr.benseddik.correctiontpspring.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBookRepository extends JpaRepository<Book, Long> {
    List<Book> findByIsAvailableTrue();

    Optional<Book> findByUuid(@NonNull UUID uuid);
}
