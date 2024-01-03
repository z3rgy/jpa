package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Users;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUser(@RequestParam(required = false, defaultValue = "") String username) {
        return ResponseEntity.ok(userService.getUserService(username));
    }
}
