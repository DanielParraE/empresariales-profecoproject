package com.profeco.consumer.repositories;

import com.profeco.consumer.controller.ProductReviewController;
import com.profeco.consumer.entities.MarketProduct;
import com.profeco.consumer.entities.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    List<ProductReview> findByMarketProduct(MarketProduct marketProduct);
}
