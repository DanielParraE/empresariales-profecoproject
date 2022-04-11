package itson.distribuidos.profecoproject.profeco.controller;

import itson.distribuidos.profecoproject.profeco.entities.Market;
import itson.distribuidos.profecoproject.profeco.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/marketspr")
public class MarketController {
    @Autowired
    private MarketService marketService;

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
        Market marketCreate = marketService.createMarket(market);
        return ResponseEntity.status(HttpStatus.CREATED).body(marketCreate);
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
