package com.profeco.controller;

import com.profeco.entities.Inconsistency;
import com.profeco.service.InconsistencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/inconsistencies")
public class InconsistencyController {

    @Autowired
    private InconsistencyService inconsistencyService;

    @GetMapping
    public ResponseEntity<List<Inconsistency>> findAll(){
        List<Inconsistency> inconsistencies = inconsistencyService.findAll();

        if (inconsistencies.isEmpty())
            return ResponseEntity.noContent().build();

        return  ResponseEntity.ok(inconsistencies);
    }
}
