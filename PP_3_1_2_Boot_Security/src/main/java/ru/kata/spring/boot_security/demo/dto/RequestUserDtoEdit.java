package ru.kata.spring.boot_security.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class RequestUserDtoEdit {
    @Email(message = "Введите корректный email")
    private String email;
    @Size(min = 2, max = 15, message = "Введите от 2 до 15 символов")
    private String name;
    @Size(min = 2, max = 35, message = "Введите от 2 до 35 символов")
    private String secondName;
    @Min(value = 2)
    private Integer age;
    @Size(min = 2, max = 15, message = "Введите от 2 до 15 символов")
    private String eyeColor;
    @Size(min = 6, max = 50, message = "От 6 до 50 символов")
    private String password;
    private Integer roleId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RequestUserDtoEdit that = (RequestUserDtoEdit) o;
        return Objects.equals(age, that.age) && Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(secondName, that.secondName) && Objects.equals(eyeColor, that.eyeColor) && Objects.equals(password, that.password) && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, secondName, age, eyeColor, password, roleId);
    }

    @Override
    public String toString() {
        return "RequestUserDtoForAdmin{" +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", eyeColor='" + eyeColor + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}

