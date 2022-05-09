package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@ResponseStatus(HttpStatus.OK)
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.findAll().stream()
                .map(ModelMapper::userDtoByUser)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ModelMapper.userDtoByUser(user);
    }

    @PostMapping
    public Long save(@RequestBody UserDto userDto) {
        return userService.save(ModelMapper.userByUserDto(userDto));
    }

    @PutMapping
    public Long update(@RequestBody UserDto userDto) {
        return userService.update(ModelMapper.userByUserDto(userDto));
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        userService.delete(id);
        return id;
    }
}
