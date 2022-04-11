package itson.distribuidos.profecoproject.consumer.service;

import itson.distribuidos.profecoproject.consumer.entities.Consumer;

import java.util.List;

public interface ConsumerService {
    public List<Consumer> listAllConsumer();
    public Consumer getConsumer(Long id);
    public Consumer createConsumer(Consumer consumer);
    public Consumer updateConsumer(Consumer consumer);
    public boolean deleteConsumer(Long id);
}
