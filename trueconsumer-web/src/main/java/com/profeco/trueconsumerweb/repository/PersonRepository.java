package com.profeco.trueconsumerweb.repository;

import com.profeco.trueconsumerweb.models.Person;

import java.util.List;

public interface PersonRepository {

    public List<Person> findAll();
    public Person findByUID(String uid);
    public String create(Person p);
    public String update(Person p);
    public String remove(String userId);

}