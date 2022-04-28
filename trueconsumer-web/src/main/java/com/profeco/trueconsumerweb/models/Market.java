package com.profeco.trueconsumerweb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Market implements Serializable {
    private int id;
    private String name;
    private String webPage;
    private String image;
    private List<MarketReview> reviews;
}
