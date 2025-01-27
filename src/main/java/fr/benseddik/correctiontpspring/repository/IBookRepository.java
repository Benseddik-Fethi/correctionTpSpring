package fr.benseddik.correctiontpspring.repository;

import fr.benseddik.correctiontpspring.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book, Long> {

    List<Book> findByIsAvailableTrue();
}
