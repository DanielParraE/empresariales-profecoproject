package com.profeco.truemarketweb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Data
public class Product implements Serializable {
    private long id;
    private String name;
    private String description;
    private Category category;
    private String image;
    private String status;
    private List<MarketProduct> marketProductList;
}
