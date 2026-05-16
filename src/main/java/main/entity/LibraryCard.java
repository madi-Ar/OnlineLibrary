package main.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate dateOfCreation;

    @OneToOne(mappedBy = "card")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "card")
    private List<BorrowRecord> borrowRecords;
}
