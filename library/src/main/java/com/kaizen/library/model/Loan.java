package com.kaizen.library.model;

import com.kaizen.library.repository.ClientRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "book_code_id")
    private BookCode bookCode;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Loan(Client client, BookCode bookCode) {
        this.setClient(client);
        this.setBookCode(bookCode);
    }
}
