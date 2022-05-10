package com.profeco.truemarketweb.repository;

import com.profeco.truemarketweb.models.Person;

import java.util.List;

public interface PersonRepository {

    public List<Person> findAll();
    public Person findByUID(String uid);
    public String create(Person p);
    public String update(Person p);
    public String remove(String userId);

}