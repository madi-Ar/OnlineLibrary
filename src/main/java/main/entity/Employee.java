package main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString(exclude = "records")
@EqualsAndHashCode(exclude = "records", callSuper = false)
public class Employee extends Person{
    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, unique = true)
    private String phone;

    @OneToMany(mappedBy = "issuedBy")
    @JsonIgnore
    private List<BorrowRecord> records;

    public Employee() {
        super();
    }
}
