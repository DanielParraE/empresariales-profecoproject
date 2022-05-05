package com.profeco.trueconsumerweb.controller;

import com.profeco.trueconsumerweb.models.Consumer;
import com.profeco.trueconsumerweb.models.Person;
import com.profeco.trueconsumerweb.repository.PersonRepository;
import com.profeco.trueconsumerweb.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private PersonRepository personRepository;

    @PostMapping(value = "/signup")
    public String postConsumer(@ModelAttribute Consumer consumer){
        Consumer consumer1 = consumerService.postConsumer(consumer);

        Person person = Person.builder().userId(consumer1.getEmail())
                .fullName(consumer1.getFullName())
                .authenticationType("Email")
                .consumerId(consumer1.getId().toString())
                .lastName(consumer.getLastName())
                .password(new BCryptPasswordEncoder().encode(consumer.getPassword()))
                .build();

        personRepository.create(person);
        return "login";

    }
}
