package se.ericnorrwing.weatherboy.service.internal.user;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.ericnorrwing.weatherboy.handler.exception.UserAlreadyExistsException;
import se.ericnorrwing.weatherboy.handler.exception.UserNotFoundException;
import se.ericnorrwing.weatherboy.model.internal.Role;
import se.ericnorrwing.weatherboy.model.internal.User;
import se.ericnorrwing.weatherboy.model.repository.UserRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;



    public UserService(UserRepository userRepository,@Lazy PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public User createUser(String username, String rawPassword) {

        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with username: " + username);
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(rawPassword));
        newUser.setRoles(Set.of(roleService.getDefaultRole()));
        return userRepository.save(newUser);
    }

    public User createUser(String username, String rawPassword, Set<Role> roles) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with username: " + username);
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(rawPassword));
        newUser.setRoles(roles);


        newUser.setRoles(roles);
        return userRepository.save(newUser);
    }

    public User updateUser(String username, String newPassword, Set<Role> newRoles) throws UserNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));

        if (newRoles != null && !newRoles.isEmpty()) {
            user.setRoles(newRoles);
        }

        return userRepository.save(user);
    }

    public void deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.deleteByUsername(username);

    }

}


