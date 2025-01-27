package fr.benseddik.correctiontpspring.service;

import fr.benseddik.correctiontpspring.domain.User;
import fr.benseddik.correctiontpspring.dto.UserRequest;
import fr.benseddik.correctiontpspring.dto.UserResponse;
import fr.benseddik.correctiontpspring.dto.mapper.UserMapper;
import fr.benseddik.correctiontpspring.error.exception.UserNotFoundException;
import fr.benseddik.correctiontpspring.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse addUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        return userMapper.toDto(userRepository.save(user));
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    public UserResponse getUserByUuid(String uuid) {
        User user = userRepository
                .findByUuid(UUID.fromString(uuid))
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toDto(user);
    }
}
