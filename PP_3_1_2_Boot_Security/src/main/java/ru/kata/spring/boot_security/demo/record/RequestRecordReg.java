package ru.kata.spring.boot_security.demo.record;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record RequestRecordReg(
        @NotEmpty
        @Email(message = "Введите корректный email")
        String email,
        @NotEmpty
        @Size(min = 2, max = 15, message = "Введите от 2 до 15 символов")
        String name,
        @Size(min = 2, max = 35, message = "Введите от 2 до 35 символов")
        String secondName,
        @Min(value = 2)
        Integer age,
        @Size(min = 2, max = 15, message = "Введите от 2 до 15 символов")
        String eyeColor,
        @NotEmpty
        @Size(min = 6, max = 50, message = "От 6 до 50 символов")
        String password,

        Integer roleId
) {
    public RequestRecordReg {
        if(roleId == null) {
            roleId = 1;
        }
    }
}
