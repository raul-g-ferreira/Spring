package com.kaizen.library.service;

import com.kaizen.library.model.Book;
import com.kaizen.library.model.Person;
import com.kaizen.library.repository.BookRepository;
import com.kaizen.library.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    public PersonService(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    @Transactional
    public void addBook(Long personId, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
        if (book.getIsAvailable()) {
            person.addBook(book); // syncs both sides
            book.setIsAvailable(Boolean.FALSE);
            bookRepository.save(book); // persist owning side
        }
    }


}
