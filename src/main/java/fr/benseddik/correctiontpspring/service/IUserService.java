package fr.benseddik.correctiontpspring.service;

import fr.benseddik.correctiontpspring.dto.UserRequest;
import fr.benseddik.correctiontpspring.dto.UserResponse;

import java.util.List;

public interface IUserService {

    UserResponse addUser(UserRequest userRequest);

    List<UserResponse> getAllUsers();

    UserResponse getUserByEmail(String email);

    UserResponse getUserByUuid(String uuid);
}
