package com.kaizen.library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;


    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Book> bookshelf = new ArrayList<>();

    public void addBook(Book book) {
        if (!this.bookshelf.contains(book)) {
            this.bookshelf.add(book);
        }
        if (book.getOwner() != this) {
            book.setOwner(this);
        }
    }

    public void removeBook(Book book) {
        if (this.bookshelf.remove(book)) {
            if (book.getOwner() == this) {
                book.setOwner(null);
            }
        }
    }

}
