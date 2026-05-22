package ru.kata.spring.boot_security.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class ResponseUserDto {
    private Integer id;
    private String email;
    private String name;
    private String secondName;
    private Integer age;
    private String eyeColor;
    private Set<String> roles;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ResponseUserDto that = (ResponseUserDto) o;
        return Objects.equals(age, that.age) && Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(secondName, that.secondName) && Objects.equals(eyeColor, that.eyeColor) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, secondName, age, eyeColor, roles);
    }

    @Override
    public String toString() {
        return "ResponseUserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", eyeColor='" + eyeColor + '\'' +
                ", roles=" + roles +
                '}';
    }
}
