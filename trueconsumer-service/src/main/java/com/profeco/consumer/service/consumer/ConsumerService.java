package com.profeco.consumer.service.consumer;

import com.profeco.consumer.entities.Consumer;

import java.util.List;

public interface ConsumerService {
    public List<Consumer> listAllConsumer();
    public Consumer getConsumer(Long id);
    public Consumer createConsumer(Consumer consumer);
    public Consumer updateConsumer(Consumer consumer);
    public Consumer deleteConsumer(Long id);
}
