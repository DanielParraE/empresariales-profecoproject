package com.profeco.trueconsumerweb.service;

import com.profeco.trueconsumerweb.models.Consumer;
import com.profeco.trueconsumerweb.models.Market;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@Service
public class ConsumerService {
    private final RestTemplate restTemplate;

    public ConsumerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Consumer postConsumer(Consumer consumer) {
        return postConsumer(consumer, null, "");
    }

    public Consumer postConsumer(Consumer consumer, byte[] bytes, String filename) {
        String url = "http://localhost:8091/consumers";
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

        body.add("consumer", consumer);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        return this.restTemplate.postForObject(url, requestEntity, Consumer.class);
    }

}
