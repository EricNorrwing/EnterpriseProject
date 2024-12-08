package se.ericnorrwing.weatherboy.model.internal.dto;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final String token;
    private final UserDTO user;

    public LoginResponse(String token, UserDTO user) {
        this.token = token;
        this.user = user;
    }

}

