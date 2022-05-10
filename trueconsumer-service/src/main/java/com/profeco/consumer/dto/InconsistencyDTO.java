package com.profeco.consumer.dto;

import com.profeco.consumer.entities.MarketProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InconsistencyDTO {

    private Long id;
    private float realPrice;
    private float publishedPrice;
    private String evidence;
    private String description;
    private Date purchasedAt;
    private Long consumerId;
    private Long marketProductId;
}
