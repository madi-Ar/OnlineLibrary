package main.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import main.Role;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends Person{
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private int age;

    @Column
    private String gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    @JsonManagedReference
    private LibraryCard card;

    public User(String email, Role role, String password, String username, int age, String gender, LibraryCard card) {
        super(email, role, password);
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.card = card;
    }

    public User() {
        super();
    }
}
