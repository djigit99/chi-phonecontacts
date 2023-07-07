package dev.djigit.phonecontacts.controller;

import dev.djigit.phonecontacts.dto.UserDto;
import dev.djigit.phonecontacts.entity.PhoneContactUser;
import dev.djigit.phonecontacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        List<PhoneContactUser> allUsers = userService.findAllUsers();
        return allUsers.stream().
                map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(user.getId());
                    userDto.setLogin(user.getLogin());
                    return userDto;
                }).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto addUser(@Valid @RequestBody PhoneContactUser user) {
        PhoneContactUser savedUser = userService.saveUser(user);
        UserDto savedUserDto = new UserDto();
        savedUserDto.setId(savedUser.getId());
        savedUserDto.setLogin(savedUser.getLogin());
        return savedUserDto;
    }

}
