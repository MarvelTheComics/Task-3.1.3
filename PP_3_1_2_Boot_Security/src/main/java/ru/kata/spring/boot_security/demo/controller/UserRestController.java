package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.mapper.UserMapper;
import ru.kata.spring.boot_security.demo.record.ResponseRecord;
import ru.kata.spring.boot_security.demo.security.UserDetailsImp;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private final UserMapper userMapper;

    @Autowired
    public UserRestController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<ResponseRecord> getUser(@AuthenticationPrincipal UserDetailsImp userDetailsImp) {
        return ResponseEntity.ok(userMapper.response(userDetailsImp.getUser()));
    }

}
