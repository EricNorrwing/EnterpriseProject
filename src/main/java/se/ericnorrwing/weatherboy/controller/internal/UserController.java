package se.ericnorrwing.weatherboy.controller.internal;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/register")
    public String registerNewUser(OAuth2AuthenticationToken authentication){
        return authentication.getPrincipal().getAttributes().get("login");
    }
}
