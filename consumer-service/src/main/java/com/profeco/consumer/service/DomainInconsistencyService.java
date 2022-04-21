package com.profeco.consumer.service;

import com.profeco.consumer.entities.Inconsistency;
import com.profeco.consumer.repositories.InconsistencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainInconsistencyService implements InconsistencyService {
    private final InconsistencyRepository inconsistencyRepository;

    @Override
    public List<Inconsistency> listAllComplain() {
        return inconsistencyRepository.findAll();
    }

    @Override
    public Inconsistency getComplain(Long id) {
        return inconsistencyRepository.findById(id).orElse(null);
    }

    @Override
    public Inconsistency createComplain(Inconsistency inconsistency) {

        return inconsistencyRepository.save(inconsistency);
    }

    @Override
    public Inconsistency updateComplain(Inconsistency inconsistency) {
        Inconsistency inconsistencyDB = getComplain(inconsistency.getId());
        if (inconsistencyDB == null) {
            return null;
        }
        inconsistencyDB.setAuthor(inconsistency.getAuthor());
        inconsistencyDB.setDescription(inconsistency.getDescription());
        //inconsistencyDB.setMarketID(inconsistency.getMarketID());
        //inconsistencyDB.setReason(inconsistency.getReason());
        return inconsistencyRepository.save(inconsistencyDB);
    }

    @Override
    public boolean deleteComplain(Long id) {
        Inconsistency inconsistencyDB = getComplain(id);
        if (inconsistencyDB == null) {
            return false;
        }
        inconsistencyRepository.delete(inconsistencyDB);
        return true;
    }
}
