package com.profeco.truemarketweb.service;

import com.profeco.truemarketweb.models.Market;
import com.profeco.truemarketweb.models.Market;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.PrinterMakeAndModel;

@Service
public class MarketService {
    private final RestTemplate restTemplate;

    @Value("${backend.consumer-service.url}")
    private String baseUrl;

    public MarketService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public Market[] getMarkets() {
        String url = "http://localhost:8091/markets";
        return this.restTemplate.getForObject(url, Market[].class);
    }

    public Market getMarket(Long id) {
        String url = "http://localhost:8091/markets/" + id;
        return this.restTemplate.getForObject(url, Market.class);
    }

    public Market[] getMarketByName(String name) {
        String url = "http://localhost:8091/Markets?name=" + name;
        return this.restTemplate.getForObject(url, Market[].class);
    }

    public Market[] getMarketsByMarketId(Long id) {
        String url = "http://localhost:8091/Markets?marketId=" + id;
        return this.restTemplate.getForObject(url, Market[].class);
    }

    public Market[] getMarketsByMarketIdAndName(Long id, String name){
        String url = "http://localhost:8091/markets/" + id + "/Markets?name=" + name;
        return this.restTemplate.getForObject(url, Market[].class);
    }

    public Market postMarket(Market market, byte[] bytes, String filename){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        if (bytes != null){
            // This nested HttpEntiy is important to create the correct
            // Content-Disposition entry with metadata "name" and "filename"
            MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
            ContentDisposition contentDisposition = ContentDisposition
                    .builder("form-data")
                    .name("file")
                    .filename(filename)
                    .build();

            fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
            HttpEntity<byte[]> fileEntity = new HttpEntity<>(bytes, fileMap);
            body.add("file", fileEntity);
        }

        body.add("market", market);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);


            String url =  baseUrl + "/markets";
            return this.restTemplate.postForObject(url, requestEntity, Market.class);

    }
}
