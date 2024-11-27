package se.ericnorrwing.weatherboy.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import se.ericnorrwing.weatherboy.service.internal.user.RoleService;
import se.ericnorrwing.weatherboy.service.internal.user.UserService;


@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    private final UserService userService;
    private final RoleService roleService;
    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(UserService userService, RoleService roleService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {

        return new OAuth2LoginSuccessHandler(userService, roleService, userDetailService);
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers("/api/test").hasAuthority("USER")
                                .anyRequest()
                                .authenticated()
                )
                .userDetailsService(userDetailsService)
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .successHandler(successHandler()));
        return http.build();
    }



}
