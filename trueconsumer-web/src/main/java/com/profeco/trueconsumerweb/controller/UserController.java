package com.profeco.trueconsumerweb.controller;

import java.util.List;

import com.profeco.trueconsumerweb.models.Person;
import com.profeco.trueconsumerweb.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private PersonRepository personRepo;

    @PostMapping
    public ResponseEntity<String> bindLdapPerson(@RequestBody Person person) {
        String result = personRepo.create(person);
        return ResponseEntity.ok(result);
    }
    @PutMapping
    public ResponseEntity<String> rebindLdapPerson(@RequestBody Person person) {
        String result = personRepo.update(person);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Person>> retrieve() {
        List<Person> personList = personRepo.findAll();
        if (personList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(personList);
    }
    @DeleteMapping
    public ResponseEntity<String> unbindLdapPerson(@RequestParam(name = "userId") String userId) {
        String result = personRepo.remove(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
