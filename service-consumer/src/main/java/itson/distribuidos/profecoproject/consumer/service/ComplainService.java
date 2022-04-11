package itson.distribuidos.profecoproject.consumer.service;

import itson.distribuidos.profecoproject.consumer.entities.Complain;

import java.util.List;

public interface ComplainService {
    public List<Complain> listAllComplain();
    public Complain getComplain(Long id);
    public Complain createComplain(Complain complain);
    public Complain updateComplain(Complain complain);
    public boolean deleteComplain(Long id);
}
