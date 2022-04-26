package com.profeco.market.controller;

import com.profeco.market.dto.MarketDTO;
import com.profeco.market.entities.Market;
import com.profeco.market.queues.MarketQueueConfig;
import com.profeco.market.service.MarketService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/markets")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @Autowired
    private RabbitTemplate template;

    @GetMapping
    public ResponseEntity<List<Market>> listMarkets() {
        List<Market> markets = marketService.listAllMarket();
        if (markets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(markets);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Market> marketById(@PathVariable(value = "id", required = true) Long id) {
        List<Market> consumers = marketService.listAllMarket();
        if (consumers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Market mk = marketService.getMarket(id);
        return ResponseEntity.ok(mk);
    }

    @PostMapping
    public ResponseEntity<Market> createMarket(@RequestBody Market market) {
        Market marketCreated = marketService.createMarket(market);

        MarketDTO marketDTO = MarketDTO.builder()
                .UUID(UUID.randomUUID().toString())
                .name(marketCreated.getName())
                .rfc(marketCreated.getRfc())
                .messageDate(new Date())
                .image(marketCreated.getImage())
                .build();

        // publish market created event
        template.convertAndSend(MarketQueueConfig.EXCHANGE,
                MarketQueueConfig.ROUTING_KEY, marketDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(marketCreated);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Market> updateConsumer(@PathVariable(value = "id") Long id, @RequestBody Market market) {
        market.setId(id);
        Market marketDB = marketService.updateMarket(market);
        if (marketDB == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marketDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteConsumer(@PathVariable(value = "id") Long id) {
        boolean deleted = marketService.deleteMarket(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("[ DELETED ]");
    }

}
