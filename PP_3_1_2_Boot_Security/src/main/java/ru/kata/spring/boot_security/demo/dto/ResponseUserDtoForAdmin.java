package ru.kata.spring.boot_security.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class ResponseUserDtoForAdmin {
    private Integer id;
    private String email;
    private String name;
    private String secondName;
    private Integer age;
    private String eyeColor;
    private String password;
    private Set<String> roles;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ResponseUserDtoForAdmin that = (ResponseUserDtoForAdmin) o;
        return Objects.equals(age, that.age) && Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(secondName, that.secondName) && Objects.equals(eyeColor, that.eyeColor) && Objects.equals(password, that.password) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, secondName, age, eyeColor, password, roles);
    }

    @Override
    public String toString() {
        return "ResponseUserDtoForAdmin{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", eyeColor='" + eyeColor + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
