package fr.benseddik.correctiontpspring.repository;

import fr.benseddik.correctiontpspring.domain.Borrowing;
import fr.benseddik.correctiontpspring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findByUserAndReturnDateIsNull(User user);


}
