package se.ericnorrwing.weatherboy.configuration.security;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import se.ericnorrwing.weatherboy.handler.exception.RoleNotFoundException;
import se.ericnorrwing.weatherboy.model.internal.Role;
import se.ericnorrwing.weatherboy.service.internal.user.RoleService;
import se.ericnorrwing.weatherboy.service.internal.user.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class InitializeApp {

    private final RoleService roleService;
    private final UserService userService;

    public InitializeApp(RoleService roleService,@Lazy UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    //TODO


    public void initializeApp() {

        createAdminUserIfNotExists();
        assignAdminRoleToUser();
    }

    private void createAdminUserIfNotExists() {
        String username = "EricN";
        if (userService.findUserByUsername(username).isEmpty()) {

            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findByName("ROLE_ADMIN").get());
            userService.createUser(username, "any12345", roles);
        }
    }

    private void assignAdminRoleToUser() {
        String username = "EricN";
        userService.findUserByUsername(username).ifPresent(user -> {

            try {
                userService.assignAdmin(user);
            } catch (RoleNotFoundException e) {
                throw new IllegalStateException("Failed to assign admin role: " + e.getMessage(), e);
            }
        });
    }
}
