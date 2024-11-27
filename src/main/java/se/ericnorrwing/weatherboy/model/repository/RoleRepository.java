package se.ericnorrwing.weatherboy.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ericnorrwing.weatherboy.model.internal.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}

