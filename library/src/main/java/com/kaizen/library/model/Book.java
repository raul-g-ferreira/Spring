package com.kaizen.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    @Nonnull
    private String title;
    private String category;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private Person owner;

    // keep both sides in sync when changing owner
    public void setOwner(Person newOwner) {
        if (this.owner == newOwner) return;

        Person oldOwner = this.owner;
        this.owner = newOwner;

        if (oldOwner != null && oldOwner.getBookshelf() != null) {
            oldOwner.getBookshelf().remove(this);
        }
        if (newOwner != null) {
            if (newOwner.getBookshelf() == null) {
                // defensive: initialize if null
                // Note: Lombok-generated setter won't override this helper
                newOwner.setBookshelf(new java.util.ArrayList<>());
            }
            if (!newOwner.getBookshelf().contains(this)) {
                newOwner.getBookshelf().add(this);
            }
        }
    }
}
