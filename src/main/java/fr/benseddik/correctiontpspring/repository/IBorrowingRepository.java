package fr.benseddik.correctiontpspring.repository;

import fr.benseddik.correctiontpspring.domain.Borrowing;
import fr.benseddik.correctiontpspring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBorrowingRepository extends JpaRepository<Borrowing, Long> {

    /*
    permet d’obtenir les livres actuellement empruntés par un utilisateur.
     */
    List<Borrowing> findByUserAndReturnDateIsNull(User user);
}
