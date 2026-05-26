package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String login(){
        return "shared/login";
    }

    @GetMapping("/login/registration")
    public String registration() {
        return "shared/registration";
    }
    @GetMapping("/users")
    public String users() {
        return "authtorized/admin";
    }

    @GetMapping("/me")
    public String me() {
        return "authtorized/me";
    }

}
