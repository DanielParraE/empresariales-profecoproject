package com.profeco.truemarketweb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Consumer implements Serializable {
    private Long id;
    private String fullName;
    private String rfc;
    private String phoneNumber;
    private String email;
    private String image;
    private Date createdAt;
    private String  status;
}
