package com.profeco.truemarketweb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Review implements Serializable {

    private Long id;
    private String comment;
    private float rating;
    private Date createdAt;
    private Date updatedAt;
    private String status;
}
