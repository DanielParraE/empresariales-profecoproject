package com.profeco.consumer.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_review")
public class ProductReview extends Review{
    @JsonBackReference(value = "market-product-productreview")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_product_id")
    private MarketProduct marketProduct;

}
