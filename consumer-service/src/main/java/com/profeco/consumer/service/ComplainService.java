package com.profeco.consumer.service;

import com.profeco.consumer.entities.Complain;

import java.util.List;

public interface ComplainService {
    public List<Complain> listAllComplain();
    public Complain getComplain(Long id);
    public Complain createComplain(Complain complain);
    public Complain updateComplain(Complain complain);
    public boolean deleteComplain(Long id);
}
