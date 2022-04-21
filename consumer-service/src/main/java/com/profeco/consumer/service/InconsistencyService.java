package com.profeco.consumer.service;

import com.profeco.consumer.entities.Inconsistency;

import java.util.List;

public interface InconsistencyService {
    public List<Inconsistency> listAllComplain();
    public Inconsistency getComplain(Long id);
    public Inconsistency createComplain(Inconsistency inconsistency);
    public Inconsistency updateComplain(Inconsistency inconsistency);
    public boolean deleteComplain(Long id);
}
