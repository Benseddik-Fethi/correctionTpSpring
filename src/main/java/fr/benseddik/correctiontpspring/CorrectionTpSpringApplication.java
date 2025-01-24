package fr.benseddik.correctiontpspring;

import fr.benseddik.correctiontpspring.domain.Book;
import fr.benseddik.correctiontpspring.domain.Borrowing;
import fr.benseddik.correctiontpspring.domain.User;
import fr.benseddik.correctiontpspring.repository.IBookRepository;
import fr.benseddik.correctiontpspring.repository.IBorrowingRepository;
import fr.benseddik.correctiontpspring.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class CorrectionTpSpringApplication implements CommandLineRunner {

    private final IUserRepository userRepository;
    private final IBookRepository bookRepository;
    private final IBorrowingRepository borrowingRepository;

    public static void main(String[] args) {
        SpringApplication.run(CorrectionTpSpringApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0 && bookRepository.count() == 0) {
            loadInitialData();
        }
    }

    private void loadInitialData() {
        log.debug("🔹 Initialisation des données...");

        // Création des utilisateurs
        User user1 = new User();
        user1.setName("Alice Dupont");
        user1.setEmail("alice@example.com");

        User user2 = new User();
        user2.setName("Bob Martin");
        user2.setEmail("bob@example.com");

        userRepository.save(user1);
        userRepository.save(user2);

        // Création des livres
        Book book1 = new Book();
        book1.setTitle("Spring Boot pour les Nuls");
        book1.setAuthor("John Doe");
        book1.setAvailable(true);

        Book book2 = new Book();
        book2.setTitle("Architecture Microservices");
        book2.setAuthor("Jane Smith");
        book2.setAvailable(true);

        Book book3 = new Book();
        book3.setTitle("Design Patterns en Java");
        book3.setAuthor("Eric Gamma");
        book3.setAvailable(true);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // Création d'un emprunt
        Borrowing borrowing = new Borrowing();
        borrowing.setUser(user1);
        borrowing.setBook(book1);
        borrowing.setBorrowDate(LocalDate.now());
        borrowing.setReturnDate(null);

        book1.setAvailable(false); // Le livre est emprunté

        borrowingRepository.save(borrowing);
        bookRepository.save(book1);

        log.debug("✅ Données initiales insérées avec succès !");
    }

}
