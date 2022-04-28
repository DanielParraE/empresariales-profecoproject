package com.profeco.consumer.market;

import com.profeco.consumer.entities.Market;
import com.profeco.consumer.repositories.MarketRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class MarketRepositoryMockTest {

    @Autowired
    private MarketRepository marketRepository;

    @Test
    public void test_FindAllMarkets() {

        List<Market> founds = marketRepository.findAll();

        Assertions.assertThat(founds.size()).isEqualTo(2);
    }
}
