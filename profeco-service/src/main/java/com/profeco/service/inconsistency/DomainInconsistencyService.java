package com.profeco.service.inconsistency;

import com.profeco.entities.Inconsistency;
import com.profeco.repositories.InconsistencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainInconsistencyService implements InconsistencyService{

    @Autowired
    private InconsistencyRepository inconsistencyRepository;

    @Override
    public Inconsistency createInconsistency(Inconsistency inconsistency) {
        return inconsistencyRepository.save(inconsistency);
    }

    @Override
    public List<Inconsistency> findAll() {
        return inconsistencyRepository.findAll();
    }

    @Override
    public Inconsistency findGetById(Long id) {
        return inconsistencyRepository.findById(id).orElse(null);
    }
}
