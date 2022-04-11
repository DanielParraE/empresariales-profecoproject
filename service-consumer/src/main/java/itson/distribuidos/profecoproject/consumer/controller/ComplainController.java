package itson.distribuidos.profecoproject.consumer.controller;

import itson.distribuidos.profecoproject.consumer.client.MarketClient;
import itson.distribuidos.profecoproject.consumer.entities.Complain;
import itson.distribuidos.profecoproject.consumer.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/complains")
public class ComplainController {

    @Autowired
    private ComplainService complainService;

    @Autowired
    private MarketClient marketClient;

    @GetMapping
    public ResponseEntity<List<Complain>> listComplain() {
        List<Complain> complains = complainService.listAllComplain();
        if (complains.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        complains.forEach(cm -> {
            cm.setMarket(marketClient.marketById(cm.getMarketID()).getBody());
        });
        return ResponseEntity.ok(complains);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Complain> complainById(@PathVariable(value = "id", required = true) Long id) {
        List<Complain> complains = complainService.listAllComplain();
        if (complains.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Complain cm = complainService.getComplain(id);
        cm.setMarket(marketClient.marketById(cm.getMarketID()).getBody());
        return ResponseEntity.ok(cm);
    }

    @PostMapping
    public ResponseEntity<Complain> createComplain(@RequestBody Complain complain) {
        Complain complainCreate = complainService.createComplain(complain);
        return ResponseEntity.status(HttpStatus.CREATED).body(complainCreate);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Complain> updateComplain(@PathVariable(value = "id") Long id, @RequestBody Complain complain) {
        complain.setId(id);
        Complain complainDB = complainService.updateComplain(complain);
        if (complainDB == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(complainDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteComplain(@PathVariable(value = "id") Long id) {
        boolean deleted = complainService.deleteComplain(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("[ DELETED ]");
    }
}
