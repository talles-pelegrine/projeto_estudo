package com.study.Study.service;

import com.study.Study.entity.Person;
import com.study.Study.repository.IPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    IPersonRepository iPersonRepository;

    public ResponseEntity<Person> create(Person person) {
        logger.info("CREATING PERSON - SERVICE");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(iPersonRepository.save(person));
    }

    public ResponseEntity<List<Person>> findAll() {
        logger.info("FIND ALL PERSONS - SERVICE");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iPersonRepository.findAll());
    }

    public ResponseEntity<Person> findById(Long id) {
        logger.info("FIND BY ID PERSON - SERVICE");
        Optional<Person> personOptional = iPersonRepository.findById(id);
        if(personOptional.isEmpty()) {
            logger.info("PERSON NOT FOUND - SERVICE");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personOptional.get());
    }

    public ResponseEntity<Person> update(Long id, Person person) {
        logger.info("UPDATE PERSON - SERVICE");
        Optional<Person> personOptional = iPersonRepository.findById(id);
        if(personOptional.isEmpty()) {
            logger.info("PERSON NOT FOUND - SERVICE");
            return ResponseEntity.notFound().build();
        }
        BeanUtils.copyProperties(person, personOptional.get(), "id");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iPersonRepository.save(personOptional.get()));
    }

    public void delete(Long id) {
        logger.info("DELETE PERSON - SERVICE");
        Optional<Person> personOptional = iPersonRepository.findById(id);
        if(personOptional.isEmpty()) {
            logger.info("PERSON NOT FOUND - SERVICE");
            return;
        }
        iPersonRepository.deleteById(id);
    }

}
