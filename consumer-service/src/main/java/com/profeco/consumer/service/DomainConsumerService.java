package com.profeco.consumer.service;

import com.profeco.consumer.entities.Consumer;
import com.profeco.consumer.repositories.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainConsumerService implements ConsumerService {

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
        Consumer consumerDB = consumerRepository.findByRfc(consumer.getRfc());
        if (consumerDB != null) return consumerDB;

        consumer.setStatus("CREATED");
        consumer.setCreatedAt(new Date());
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
        //consumerDB.setRfc(consumer.getRfc());
        consumerDB.setEmail(consumer.getEmail());
        return consumerRepository.save(consumerDB);
    }

    @Override
    public Consumer deleteConsumer(Long id) {
        Consumer consumerDB = getConsumer(id);
        if (consumerDB == null) return null;

        //consumerRepository.delete(consumerDB);
        consumerDB.setStatus("DELETED");
        return consumerRepository.save(consumerDB);
    }
}
