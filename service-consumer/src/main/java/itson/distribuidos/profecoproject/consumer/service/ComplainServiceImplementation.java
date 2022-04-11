package itson.distribuidos.profecoproject.consumer.service;

import itson.distribuidos.profecoproject.consumer.entities.Complain;
import itson.distribuidos.profecoproject.consumer.entities.Consumer;
import itson.distribuidos.profecoproject.consumer.repositories.ComplainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplainServiceImplementation implements ComplainService {
    private final ComplainRepository complainRepository;

    @Override
    public List<Complain> listAllComplain() {
        return complainRepository.findAll();
    }

    @Override
    public Complain getComplain(Long id) {
        return complainRepository.findById(id).orElse(null);
    }

    @Override
    public Complain createComplain(Complain complain) {
        return complainRepository.save(complain);
    }

    @Override
    public Complain updateComplain(Complain complain) {
        Complain complainDB = getComplain(complain.getId());
        if (complainDB == null) {
            return null;
        }
        complainDB.setAuthor(complain.getAuthor());
        complainDB.setDescription(complain.getDescription());
        complainDB.setMarketID(complain.getMarketID());
        complainDB.setReason(complain.getReason());
        return complainRepository.save(complainDB);
    }

    @Override
    public boolean deleteComplain(Long id) {
        Complain complainDB = getComplain(id);
        if (complainDB == null) {
            return false;
        }
        complainRepository.delete(complainDB);
        return true;
    }
}
