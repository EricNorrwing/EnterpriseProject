package se.ericnorrwing.weatherboy.configuration.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import se.ericnorrwing.weatherboy.model.internal.Role;
import se.ericnorrwing.weatherboy.model.internal.User;
import se.ericnorrwing.weatherboy.service.internal.user.RoleService;
import se.ericnorrwing.weatherboy.service.internal.user.UserService;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;
    private final RoleService roleService;

    public OAuth2LoginSuccessHandler(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication instanceof OAuth2AuthenticationToken oauthToken) {

            Map<String, Object> attributes = oauthToken.getPrincipal().getAttributes();
            String username = (String) attributes.get("login");

            Optional<User> userOptional = userService.findUserByUsername(username);

            if (userOptional.isEmpty()) {

                Role defaultRole = roleService.getDefaultRole().orElseThrow();
                User newUser = new User();
                newUser.setUsername(username);
                newUser.getRoles().add(defaultRole);

                userService.save(newUser);
            }
        }
    }
}
