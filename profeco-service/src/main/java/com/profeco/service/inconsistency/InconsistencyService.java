package com.profeco.service.inconsistency;


import com.profeco.entities.Inconsistency;

import java.util.List;

public interface InconsistencyService {
    Inconsistency createInconsistency(Inconsistency inconsistency);
    List<Inconsistency> findAll();
    Inconsistency findGetById(Long id);
}
