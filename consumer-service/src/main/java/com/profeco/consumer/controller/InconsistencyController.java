package com.profeco.consumer.controller;

import com.profeco.consumer.service.InconsistencyService;
import com.profeco.consumer.entities.Inconsistency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/complains")
public class InconsistencyController {

    @Autowired
    private InconsistencyService inconsistencyService;

    @GetMapping
    public ResponseEntity<List<Inconsistency>> listComplain() {
        List<Inconsistency> inconsistencies = inconsistencyService.listAllComplain();
        if (inconsistencies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
//        inconsistencies.forEach(cm -> {
//            cm.setMarket(marketClient.marketById(cm.getMarketID()).getBody());
//        });
        return ResponseEntity.ok(inconsistencies);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Inconsistency> complainById(@PathVariable(value = "id", required = true) Long id) {
        List<Inconsistency> inconsistencies = inconsistencyService.listAllComplain();
        if (inconsistencies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Inconsistency cm = inconsistencyService.getComplain(id);
//        cm.setMarket(marketClient.marketById(cm.getMarketID()).getBody());
        return ResponseEntity.ok(cm);
    }

    @PostMapping
    public ResponseEntity<Inconsistency> createComplain(@RequestBody Inconsistency inconsistency) {
        Inconsistency inconsistencyCreate = inconsistencyService.createComplain(inconsistency);
        return ResponseEntity.status(HttpStatus.CREATED).body(inconsistencyCreate);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Inconsistency> updateComplain(@PathVariable(value = "id") Long id, @RequestBody Inconsistency inconsistency) {
        inconsistency.setId(id);
        Inconsistency inconsistencyDB = inconsistencyService.updateComplain(inconsistency);
        if (inconsistencyDB == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inconsistencyDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteComplain(@PathVariable(value = "id") Long id) {
        boolean deleted = inconsistencyService.deleteComplain(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("[ DELETED ]");
    }
}
