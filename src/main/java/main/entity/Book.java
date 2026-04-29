package main.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "year_of_publication", nullable = false)
    private int year;

    @ManyToMany(mappedBy = "books")
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "book_store_id")
    private BookStore bs;
}
