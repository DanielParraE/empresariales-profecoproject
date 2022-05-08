package com.profeco.profecocontrolweb.service;

import com.profeco.profecocontrolweb.model.Inconsistency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class InconsistencyService {

    private RestTemplate restTemplate;

    @Value("${backend.profeco-service.url}")
    private String baseUrl;

    public InconsistencyService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Inconsistency[] getInconsistencies(){
        String url = baseUrl + "/inconsistencies";
        return this.restTemplate.getForObject(url, Inconsistency[].class);
    }
}
