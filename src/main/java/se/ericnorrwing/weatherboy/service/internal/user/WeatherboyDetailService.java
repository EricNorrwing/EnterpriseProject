package se.ericnorrwing.weatherboy.service.internal.user;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.ericnorrwing.weatherboy.configuration.security.SecurityConfiguration;
import se.ericnorrwing.weatherboy.model.internal.SecurityUser;
import se.ericnorrwing.weatherboy.model.internal.User;

@Service
public class WeatherboyDetailService implements UserDetailsService {

    private final UserService userService;

    public WeatherboyDetailService(UserService userService) {  // Lazy initialization here
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new SecurityUser(user);
    }
}
