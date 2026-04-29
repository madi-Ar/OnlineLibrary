package main.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "book_store")
@Getter
@Setter
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "bs")
    private List<Book> books;

    @OneToMany(mappedBy = "bookStore")
    private List<User> users;

    @OneToOne
    @JoinColumn(name = "BPH_id")
    private BookPublishingHouse bph;
}
