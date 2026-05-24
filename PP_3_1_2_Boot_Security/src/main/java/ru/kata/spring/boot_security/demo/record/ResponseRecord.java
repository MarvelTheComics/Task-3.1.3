package ru.kata.spring.boot_security.demo.record;

import java.util.Set;

public record ResponseRecord(
        Integer id,
        String email,
        String name,
        String secondName,
        Integer age,
        String eyeColor,
        Set<String>roles
) { }