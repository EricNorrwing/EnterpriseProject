package se.ericnorrwing.weatherboy.service.internal.user;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import se.ericnorrwing.weatherboy.model.internal.Role;
import se.ericnorrwing.weatherboy.model.internal.User;
import se.ericnorrwing.weatherboy.model.repository.UserRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }



    public Optional<User> createUserFromOAuth2Authentication(OAuth2AuthenticationToken authentication) {
        Map<String, Object> attributes = authentication.getPrincipal().getAttributes();
        String username = (String) attributes.get("login");

        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            return existingUser;
        }


        User newUser = User.builder()
                .username(username)
                .build();

        Role defaultRole = roleService.getDefaultRole().orElseThrow();
        newUser.getRoles().add(defaultRole);

        userRepository.save(newUser);
        return Optional.of(newUser);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
