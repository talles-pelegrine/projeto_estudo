package com.study.Study.controller;

import com.study.Study.entity.Person;
import com.study.Study.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody Person person) {
        logger.info("CREATING PERSON - CONTROLLER");
        return personService.create(person);
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        logger.info("FIND ALL PERSONS - CONTROLLER");
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable("id") Long id) {
        logger.info("FIND BY ID - CONTROLLER");
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable("id") Long id, @Valid @RequestBody Person person) {
        logger.info("UPDATE PERSON - CONTROLLER");
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        logger.info("DELETE PERSON - CONTROLLER");
        personService.delete(id);
    }

}