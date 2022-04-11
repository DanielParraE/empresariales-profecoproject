package itson.distribuidos.profecoproject.consumer.service;

import itson.distribuidos.profecoproject.consumer.entities.Consumer;
import itson.distribuidos.profecoproject.consumer.repositories.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImplementation implements ConsumerService {

    private final ConsumerRepository consumerRepository;

    @Override
    public List<Consumer> listAllConsumer() {
        return consumerRepository.findAll();
    }

    @Override
    public Consumer getConsumer(Long id) {
        return consumerRepository.findById(id).orElse(null);
    }

    @Override
    public Consumer createConsumer(Consumer consumer) {
        return consumerRepository.save(consumer);
    }

    @Override
    public Consumer updateConsumer(Consumer consumer) {
        Consumer consumerDB = getConsumer(consumer.getId());
        if (consumerDB == null) {
            return null;
        }
        consumerDB.setFullName(consumer.getFullName());
        consumerDB.setPhoneNumber(consumer.getPhoneNumber());
        consumerDB.setRfc(consumer.getRfc());
        consumerDB.setEmail(consumer.getEmail());
        return consumerRepository.save(consumerDB);
    }

    @Override
    public boolean deleteConsumer(Long id) {
        Consumer consumerDB = getConsumer(id);
        if (consumerDB == null) {
            return false;
        }
        consumerRepository.delete(consumerDB);
        return true;
    }
}
