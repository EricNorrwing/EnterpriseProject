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
        return userRepository.save(newUser);
    }

    public User updateUser(String username, String password, Set<Role> roles) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        User userToUpdate = userOptional.get();
        if (password != null && !password.isEmpty()) {
            userToUpdate.setPassword(password);
        }

        if (roles != null && !roles.isEmpty()) {
            userToUpdate.setRoles(roles);
        }

        return userRepository.save(userToUpdate);
    }

    public void deleteUserByUsername(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.deleteByUsername(username);

    }

    public void assignAdmin(User user) {
        Role adminRole = roleService.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new IllegalStateException("ROLE_ADMIN not found"));


        Set<Role> updatedRoles = new java.util.HashSet<>(Set.copyOf(user.getRoles()));
        updatedRoles.add(adminRole);
        user.setRoles(updatedRoles);

        userRepository.save(user);
    }
}


