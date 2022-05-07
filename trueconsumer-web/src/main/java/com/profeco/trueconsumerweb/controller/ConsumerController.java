package com.profeco.trueconsumerweb.controller;

import com.profeco.trueconsumerweb.models.Consumer;
import com.profeco.trueconsumerweb.models.Person;
import com.profeco.trueconsumerweb.repository.PersonRepository;
import com.profeco.trueconsumerweb.service.ConsumerService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
                .lastName(consumer.getSurname())
                .password(new BCryptPasswordEncoder().encode(consumer.getPassword()))
                .build();

        personRepository.create(person);
        return "login";

    }

    //@PostMapping(value = "/consumers/edit", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @PostMapping(value = "/consumers/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public String updateConsumer(@RequestPart Consumer consumer,
                                                   @RequestPart(required = false) MultipartFile file
                                 ,@PathVariable(required = true) Long id) throws IOException {
        consumer.setId(id);

        Person person = personRepository.findByUID(consumer.getEmail());
        person.setFullName(consumer.getFullName());
        person.setLastName(consumer.getSurname());
        person.setPassword(new BCryptPasswordEncoder().encode(consumer.getPassword()));

        byte[] bytes =  (file == null)? null: file.getBytes();

        personRepository.update(person);
        consumerService.updateConsumer(consumer, bytes, RandomStringUtils.randomAlphanumeric(8));
        return "index";

    }
}
