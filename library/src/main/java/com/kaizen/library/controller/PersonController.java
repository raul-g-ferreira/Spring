package com.kaizen.library.controller;

import com.kaizen.library.model.Person;
import com.kaizen.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getAll() {
        return personService.getAll();
    }


    @PostMapping
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }

    @PatchMapping("/{personId}/add-book/{bookId}")
    public ResponseEntity<Void> addBook(@PathVariable Long personId, @PathVariable Long bookId) {
        personService.addBook(personId, bookId);
        return ResponseEntity.ok().build();
    }
}
