package se.ericnorrwing.weatherboy.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.server.SecurityWebFilterChain;
import se.ericnorrwing.weatherboy.service.internal.user.RoleService;
import se.ericnorrwing.weatherboy.service.internal.user.UserService;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    private final UserService userService;
    private final RoleService roleService;

    public SecurityConfiguration(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {

        return new OAuth2LoginSuccessHandler(userService, roleService);
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers("/test").permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .successHandler(successHandler()));
        return http.build();
    }
}
