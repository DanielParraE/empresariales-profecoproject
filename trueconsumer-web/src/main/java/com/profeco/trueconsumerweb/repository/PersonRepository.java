package com.profeco.trueconsumerweb.repository;

import com.profeco.trueconsumerweb.models.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PersonRepository {

    public List<Person> retrieve();
    public String create(Person p);
    public String update(Person p);
    public String remove(String userId);

}