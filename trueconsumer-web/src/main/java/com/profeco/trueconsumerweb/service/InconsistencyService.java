package com.profeco.trueconsumerweb.service;


import com.profeco.trueconsumerweb.models.Consumer;
import com.profeco.trueconsumerweb.models.Inconsistency;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class InconsistencyService {
    private final RestTemplate restTemplate;

    public InconsistencyService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Inconsistency postInconsistency(Inconsistency inconsistency, byte[] bytes, String filename){
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

        body.add("inconsistency", inconsistency);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        String url = "http://localhost:8091/inconsistencies";
        return this.restTemplate.postForObject(url, requestEntity, Inconsistency.class);

    }
}
