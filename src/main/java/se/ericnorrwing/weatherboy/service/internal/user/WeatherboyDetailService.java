package se.ericnorrwing.weatherboy.service.internal.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.ericnorrwing.weatherboy.model.internal.SecurityUser;

@Service
public class WeatherboyDetailService implements UserDetailsService {

    private final UserService userService;

    public WeatherboyDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.
                findUserByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Error: Unable to verify user"));
    }
}
