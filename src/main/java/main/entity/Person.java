package main.entity;

import jakarta.persistence.*;
import lombok.Data;
import main.Role;

@MappedSuperclass
@Data
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 10)
    private Role role = Role.USER;

    @Column(nullable = false, length = 100)
    private String password;

    public Person(String email, Role role, String password) {
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public Person() {

    }
}
