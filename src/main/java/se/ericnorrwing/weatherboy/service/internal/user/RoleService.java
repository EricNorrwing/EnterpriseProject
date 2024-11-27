package se.ericnorrwing.weatherboy.service.internal.user;

import org.springframework.stereotype.Service;
import se.ericnorrwing.weatherboy.model.internal.Role;
import se.ericnorrwing.weatherboy.model.repository.RoleRepository;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> getDefaultRole() {
        return roleRepository.findByName("ROLE_USER");
    }

}
