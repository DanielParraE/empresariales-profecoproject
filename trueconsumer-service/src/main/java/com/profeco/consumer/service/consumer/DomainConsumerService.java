package com.profeco.consumer.service.consumer;

import com.profeco.consumer.entities.Consumer;
import com.profeco.consumer.repositories.ConsumerRepository;
import com.profeco.consumer.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainConsumerService implements ConsumerService {

    private final ConsumerRepository consumerRepository;

    @Autowired
    private StorageService storageService;

    @Override
    public List<Consumer> listAllConsumer() {
        return consumerRepository.findAll();
    }

    @Override
    public Consumer getConsumer(Long id) {
        return consumerRepository.findById(id).orElse(null);
    }

    @Override
    public Consumer createConsumer(Consumer consumer, MultipartFile image) {
        Consumer consumerDB = consumerRepository.findByRfc(consumer.getRfc());
        if (consumerDB != null) return consumerDB;

        String url =  (image == null)?
                "http://localhost:8091/files/abc-person.png"
                : storageService.store(image);

        consumer.setImage(url);

        consumer.setStatus("CREATED");
        consumer.setCreatedAt(new Date());
        return consumerRepository.save(consumer);
    }

    @Override
    public Consumer updateConsumer(Consumer consumer, MultipartFile image) {
        Consumer consumerDB = getConsumer(consumer.getId());
        if (consumerDB == null) {
            return null;
        }

        String url =  (image == null)?
                consumerDB.getImage()
                : storageService.store(image);

        consumerDB.setImage(url);

        consumerDB.setFullName(consumer.getFullName());
        consumerDB.setPhoneNumber(consumer.getPhoneNumber());
        consumerDB.setRfc(consumer.getRfc());
        consumerDB.setSurname(consumer.getSurname());
        //consumerDB.setEmail(consumer.getEmail());
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
