package bornlist.controller;

import bornlist.dto.UserDto;
import bornlist.service.UserService;
import bornlist.service.converter.UserConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bl/user/")
public class UserController {

    private UserService userService;
    private UserConverter converter;

    public UserController(UserService userService, UserConverter converter) {
        this.userService = userService;
        this.converter = converter;
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return converter.convertListToDto(userService.findAll());
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") int id) {
        return converter.convertToDto(userService.findById(id));
    }

    @PostMapping("/")
    public void createUser(@RequestBody UserDto userDto) {
        userService.create(converter.convertToEntity(userDto));
    }

    @PutMapping("/")
    public void updateUser(@RequestBody UserDto userDto) {
        userService.update(converter.convertToEntity(userDto), userDto.getId());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
    }
}
