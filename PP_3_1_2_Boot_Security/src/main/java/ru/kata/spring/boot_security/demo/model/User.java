package ru.kata.spring.boot_security.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "Name")
    private String name;
    @Column(name = "Second_name")
    private String secondName;
    @Column(name = "Age")
    private Integer age;
    @Column(name = "Eye_color")
    private String eyeColor;
    @Column(name = "Password")
    private String password;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "User_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name  = "role_id")
    )
    private Set<Role> roles;

    public User(String email, String name, String secondName, int age, String eyeColor, String password) {
        this.email = email;
        this.name = name;
        this.secondName = secondName;
        this.age = age;
        this.eyeColor = eyeColor;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(age, user.age) && Objects.equals(name, user.name) && Objects.equals(secondName, user.secondName) && Objects.equals(eyeColor, user.eyeColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, secondName, age, eyeColor, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", eyeColor='" + eyeColor + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
