package ru.kata.spring.boot_security.demo.util;

import ru.kata.spring.boot_security.demo.dto.RoleDto;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.stream.Collectors;

public final class ModelMapper {

    private ModelMapper() {
    }

    public static User userByUserDto(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRoles().stream()
                        .map(ModelMapper::roleByRoleDto)
                        .collect(Collectors.toUnmodifiableSet())
        );
    }

    public static Role roleByRoleDto(RoleDto roleDto) {
        return new Role(roleDto.getId(), roleDto.getName());
    }

    public static UserDto userDtoByUser(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getRoles().stream()
                        .map(ModelMapper::roleDtoByRole)
                        .collect(Collectors.toUnmodifiableSet())
        );
    }

    public static RoleDto roleDtoByRole(Role role) {
        return new RoleDto(role.getId(), role.getName());
    }
}
