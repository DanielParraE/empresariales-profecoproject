package com.profeco.dto;

import com.profeco.entities.Inconsistency;
import com.profeco.entities.Market;
import com.profeco.entities.MarketProduct;
import com.profeco.entities.Product;
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

    public Inconsistency convertToBO(){
        return Inconsistency.builder()
                .id(id)
                .realPrice(realPrice)
                .publishedPrice(publishedPrice)
                .evidence(evidence)
                .description(description)
                .purchasedAt(purchasedAt)
                .consumerId(consumerId)
                .marketProduct(MarketProduct.builder().id(marketProductId).build())
                .build();
    }
}
