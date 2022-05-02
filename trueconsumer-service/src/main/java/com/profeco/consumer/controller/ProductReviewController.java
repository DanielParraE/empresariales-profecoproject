package com.profeco.consumer.controller;

import com.profeco.consumer.entities.MarketProduct;
import com.profeco.consumer.entities.MarketReview;
import com.profeco.consumer.entities.ProductReview;
import com.profeco.consumer.repositories.ProductReviewRepository;
import com.profeco.consumer.service.productreview.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/productreviews")
public class ProductReviewController {

    @Autowired
    private ProductReviewService productReviewService;

    @GetMapping
    public ResponseEntity<List<ProductReview>> getProductReviewsByMarketProduct(@RequestParam(value = "marketProductId", required = true)
                                                                        Long marketProductId){
        List<ProductReview> productReviews =
                productReviewService.findProductReviewsByMarketProduct(marketProductId);

        if (productReviews.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(productReviews);
    }
}
