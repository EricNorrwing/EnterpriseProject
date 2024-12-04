package se.ericnorrwing.weatherboy.service.internal.user;

import org.springframework.stereotype.Service;
import se.ericnorrwing.weatherboy.handler.exception.RoleNotFoundException;
import se.ericnorrwing.weatherboy.model.internal.Role;
import se.ericnorrwing.weatherboy.model.repository.RoleRepository;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;


    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public void save(Role role) {
        roleRepository.save(role);
    }

    public Role getDefaultRole() {
        return roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("Default role 'ROLE_USER' not found"));
    }

    public Optional<Role> findByName(String roleName) {
        return Optional.ofNullable(roleRepository.findByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role not found: " + roleName)));
    }


}
