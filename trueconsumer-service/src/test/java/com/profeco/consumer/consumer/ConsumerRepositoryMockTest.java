package com.profeco.consumer.consumer;

import com.profeco.consumer.SecurityConfig;
import com.profeco.consumer.entities.Consumer;
import com.profeco.consumer.repositories.ConsumerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@DataJpaTest
public class ConsumerRepositoryMockTest {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Test
    public void test_saveConsumerAndFindAll() {
        Consumer consumer01 = Consumer.builder()
                .fullName("Bodoque")
                .rfc("BODO420112PP1")
                .email("bodoque@gmail.com")
                .phoneNumber("6449873211")
                .build();

        consumerRepository.save(consumer01);

        List<Consumer> founds = consumerRepository.findAll();

        Assertions.assertThat(founds.size()).isEqualTo(4);
    }

}
