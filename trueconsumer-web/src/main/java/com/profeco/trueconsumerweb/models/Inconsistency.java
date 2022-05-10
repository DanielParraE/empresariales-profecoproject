package com.profeco.trueconsumerweb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Inconsistency  implements Serializable {
    private Long id;
    private float realPrice;
    private float publishedPrice;
    private String description;
    private String evidence;
    private Date purchasedAt;
    private Consumer author;
    private MarketProduct marketProduct;
}
