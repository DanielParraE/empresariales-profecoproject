package com.profeco.consumer.service.consumer;

import com.profeco.consumer.entities.Consumer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ConsumerService {
    public List<Consumer> listAllConsumer();
    public Consumer getConsumer(Long id);
    public Consumer createConsumer(Consumer consumer, MultipartFile image);
    public Consumer updateConsumer(Consumer consumer, MultipartFile image);
    public Consumer deleteConsumer(Long id);
}
