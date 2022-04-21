package com.profeco.consumer.consumer;

import com.profeco.consumer.entities.Consumer;
import com.profeco.consumer.repositories.ConsumerRepository;
import com.profeco.consumer.service.ConsumerService;
import com.profeco.consumer.service.DomainConsumerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ConsumerServiceMockTest {
    @Mock
    private ConsumerRepository consumerRepository;

    private ConsumerService consumerService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        consumerService = new DomainConsumerService(consumerRepository);
        Consumer consumer = Consumer.builder()
                .id(1L)
                .fullName("test1")
                .email("test@gmail.com")
                .phoneNumber("463738737")
                .rfc("SDF23EWFEWF")
                .build();

        Mockito.when(consumerRepository.findById(1L))
                .thenReturn(Optional.of(consumer));
    }

    @Test
    public void test_GetConsumerById(){
        Consumer consumer = consumerService.getConsumer(1L);
        Assertions.assertThat(consumer.getFullName()).isEqualTo("test1");
    }
}
