package by.teachmeskills.eshop.dto.converts;

import by.teachmeskills.eshop.dto.RoleDto;
import by.teachmeskills.eshop.entities.Role;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.repositories.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleConverter {
    private final RoleRepository roleRepository;

    public RoleConverter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleDto toDto(Role role) {
        return Optional.ofNullable(role).map(role1 -> RoleDto.builder()
                        .name(role1.getName())
                        .build()).
                orElse(null);
    }

    public Role fromDto(RoleDto roleDto) throws RepositoryExceptions {
        return roleRepository.findByName(roleDto.getName());
    }
}