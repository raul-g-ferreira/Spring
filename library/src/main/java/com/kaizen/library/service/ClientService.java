package com.kaizen.library.service;

import com.kaizen.library.exception.ClientNotFoundException;
import com.kaizen.library.model.Book;
import com.kaizen.library.model.Client;
import com.kaizen.library.repository.BookRepository;
import com.kaizen.library.repository.ClientRepository;
import com.kaizen.library.repository.LoanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    private final LoanRepository loanRepository;
    private final ClientRepository clientRepository;

    public ClientService(LoanRepository loanRepository, ClientRepository clientRepository) {
        this.loanRepository = loanRepository;
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public void delete(Long id) {
       boolean hasLoan = loanRepository.findAll().stream().anyMatch(loan -> loan.getClient().getId().equals(id));
        if (hasLoan) {
            throw new IllegalStateException("Client has active loans and cannot be deleted.");
        }
        clientRepository.deleteById(id);
    }


    public Client findById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(ClientNotFoundException::new);
    }
}
