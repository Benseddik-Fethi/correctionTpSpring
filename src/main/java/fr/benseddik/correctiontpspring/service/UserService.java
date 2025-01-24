package fr.benseddik.correctiontpspring.service;

import fr.benseddik.correctiontpspring.domain.User;
import fr.benseddik.correctiontpspring.exception.UserNotFoundException;
import fr.benseddik.correctiontpspring.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur avec ID " + id + " non trouvé"));
    }
}
