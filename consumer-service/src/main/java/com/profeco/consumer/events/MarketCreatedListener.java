package com.profeco.consumer.events;

import com.profeco.consumer.dto.MarketDTO;
import com.profeco.consumer.queues.MarketQueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MarketCreatedListener {

    @RabbitListener(queues = MarketQueueConfig.QUEUE)
    public void listener(MarketDTO marketDTO) {
        System.out.println(marketDTO);
    }
}
