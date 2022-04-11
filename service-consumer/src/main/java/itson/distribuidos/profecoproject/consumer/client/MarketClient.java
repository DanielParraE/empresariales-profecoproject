package itson.distribuidos.profecoproject.consumer.client;

import itson.distribuidos.profecoproject.consumer.models.Market;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "market-service")
public interface MarketClient {
    @GetMapping(value = "/markets/{id}")
    public ResponseEntity<Market> marketById(@PathVariable(value = "id", required = true) Long id);
}
