package com.profeco.consumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String NEW_INCONSISTENCY_QUEUE = "new_inconsistency_queue";
    public static final String NEW_MARKET_QUEUE = "new_market_queue";
    public static final String EXCHANGE = "trueconsumer_exchange";
    public static final String NEW_INCONSISTENCY_ROUTING_KEY = "new_inconsistency_routing_key";
    public static final String NEW_MARKET_ROUTING_KEY = "new_market_routing_key";

    @Bean
    public Queue queueNewInconsistency(){
        return new Queue(NEW_INCONSISTENCY_QUEUE);
    }

    @Bean
    public Queue queueNewMarket(){
        return new Queue(NEW_MARKET_QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding bindingNewInconsistency(TopicExchange exchange){
        return BindingBuilder
                .bind(queueNewInconsistency())
                .to(exchange)
                .with(NEW_INCONSISTENCY_ROUTING_KEY);
    }

    @Bean
    public Binding bindingNewMarket(TopicExchange exchange){
        return BindingBuilder
                .bind(queueNewMarket())
                .to(exchange)
                .with(NEW_MARKET_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
