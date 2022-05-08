package com.profeco.profecocontrolweb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Inconsistency implements Serializable {
    private Long id;
    private float realPrice;

    private float publishedPrice;


    private String evidence;

    private String description;

    private Date purchasedAt;

    private Long consumerId;

    private Product product;

    private Market market;
}
