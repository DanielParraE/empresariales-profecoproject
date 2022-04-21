package com.profeco.consumer;

import com.profeco.consumer.entities.Consumer;
import com.profeco.consumer.repositories.ComplainRepository;
import com.profeco.consumer.repositories.ConsumerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class BaseRepositoryMockTest {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ComplainRepository complainRepository;

    @Test
    public void whenFindAll_thenReturnListConsumer() {
        Consumer consumer01 = Consumer.builder()
                .fullName("Bodoque")
                .rfc("1234567891126")
                .email("example4@gmail.com")
                .phoneNumber("6449873211")
                .build();
        consumerRepository.save(consumer01);
        List<Consumer> founds = consumerRepository.findAll();
        Assertions.assertThat(founds.size()).isEqualTo(4);
    }

}
