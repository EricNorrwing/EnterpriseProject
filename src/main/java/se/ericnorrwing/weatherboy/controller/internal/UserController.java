package se.ericnorrwing.weatherboy.controller.internal;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import se.ericnorrwing.weatherboy.handler.exception.UserNotFoundException;
import se.ericnorrwing.weatherboy.model.internal.User;
import se.ericnorrwing.weatherboy.service.internal.user.UserService;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    //TODO REMOVE?
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/test")
    public String testPasswordHashing() {
        String plainPassword = "any12345";
        String hashedPassword = passwordEncoder.encode(plainPassword);
        return "Plain password: " + plainPassword + "\nHashed password: " + hashedPassword;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.createUser(user.getUsername(), user.getPassword());
        return ResponseEntity.status(201).body(savedUser);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username,
                                           @RequestBody User user) {
        try {

            User updatedUser = userService.updateUser(username, user.getPassword(), user.getRoles());
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {

            return ResponseEntity.status(404).body(null);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        try {
            userService.deleteUserByUsername(username);
            return ResponseEntity.ok("User deleted successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            log.error("Error deleting user: {}", username, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user");
        }
    }

}
