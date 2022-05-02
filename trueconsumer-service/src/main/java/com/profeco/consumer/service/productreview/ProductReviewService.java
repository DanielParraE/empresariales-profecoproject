package com.profeco.consumer.service.productreview;


import com.profeco.consumer.controller.ProductReviewController;
import com.profeco.consumer.entities.ProductReview;

import java.util.List;

public interface ProductReviewService {
    List<ProductReview> findProductReviewsByMarketProduct(Long id);
}
