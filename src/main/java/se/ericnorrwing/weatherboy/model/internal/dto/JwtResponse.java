package se.ericnorrwing.weatherboy.model.internal.dto;

import lombok.Getter;

@Getter
public class JwtResponse {
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

}

