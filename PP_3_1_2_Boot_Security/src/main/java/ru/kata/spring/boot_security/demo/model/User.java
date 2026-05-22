package ru.kata.spring.boot_security.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
