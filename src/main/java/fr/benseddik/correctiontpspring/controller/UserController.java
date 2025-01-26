package fr.benseddik.correctiontpspring.controller;

import fr.benseddik.correctiontpspring.dto.UserRequest;
import fr.benseddik.correctiontpspring.dto.UserResponse;
import fr.benseddik.correctiontpspring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {
        log.info("add user request: {}", userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userRequest));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info("get all users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserResponse> getUserByUuid(@PathVariable String uuid) {
        log.info("get user by uuid: {}", uuid);
        return ResponseEntity.ok(userService.getUserByUuid(uuid));
    }
}
