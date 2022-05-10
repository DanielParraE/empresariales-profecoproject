package com.profeco.consumer.service.inconsistency;

import com.profeco.consumer.entities.Inconsistency;

import java.util.List;

public interface InconsistencyService {
    public List<Inconsistency> listAllComplain();
    public List<Inconsistency> getComplainByMarket(Long marketId);
    public Inconsistency getComplain(Long id);
    public Inconsistency createComplain(Inconsistency inconsistency);
    public Inconsistency updateComplain(Inconsistency inconsistency);
    public boolean deleteComplain(Long id);

}
