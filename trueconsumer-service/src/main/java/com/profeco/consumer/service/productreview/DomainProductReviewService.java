package com.profeco.consumer.service.productreview;

import com.profeco.consumer.controller.ProductReviewController;
import com.profeco.consumer.entities.MarketProduct;
import com.profeco.consumer.entities.ProductReview;
import com.profeco.consumer.repositories.ProductReviewRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class DomainProductReviewService implements ProductReviewService{

    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Override
    public List<ProductReview> findProductReviewsByMarketProduct(Long id) {
        return productReviewRepository.findByMarketProduct(MarketProduct.builder().id(id).build());
    }
}
