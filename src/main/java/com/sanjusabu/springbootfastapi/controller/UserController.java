package com.sanjusabu.springbootfastapi.controller;

import com.sanjusabu.springbootfastapi.dto.UserRequestDTO;
import com.sanjusabu.springbootfastapi.entity.AppUser;
import com.sanjusabu.springbootfastapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<AppUser> createUser(@RequestBody UserRequestDTO request) {
        return userService.createUser(request);
    }

    @PutMapping("{id}")
    public ResponseEntity<AppUser> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserRequestDTO request
    ) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
