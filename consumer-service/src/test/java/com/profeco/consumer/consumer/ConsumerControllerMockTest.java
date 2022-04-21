package com.profeco.consumer.consumer;


import com.profeco.consumer.controller.ConsumerController;
import com.profeco.consumer.entities.Consumer;
import com.profeco.consumer.service.ConsumerService;
import com.profeco.consumer.service.DomainConsumerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsumerController.class)
public class ConsumerControllerMockTest {

    @MockBean
    private ConsumerService consumerService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        //consumerService = new DomainConsumerService(consumerRepository);

        Consumer consumer1 = Consumer.builder().fullName("Lokesh").build();
        Consumer consumer2 = Consumer.builder().fullName("Pepito").build();
        Consumer consumer3 = Consumer.builder().fullName("Juan").build();

        List<Consumer> consumerList = Arrays.asList(consumer1, consumer2, consumer3);
        Mockito.when(consumerService.listAllConsumer()).thenReturn(consumerList);
    }

    @Test
    public void testGetConsumers() throws Exception {
        mockMvc.perform(get("/consumers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].fullName", Matchers.is("Lokesh")))
                .andExpect(jsonPath("$[1].fullName", Matchers.is("Pepito")))
                .andExpect(jsonPath("$[2].fullName", Matchers.is("Juan")));
    }

    @Test
    public void testGetConsumer() throws Exception{
        mockMvc.perform(get("/consumers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", Matchers.is("Lokesh")));
    }
}
