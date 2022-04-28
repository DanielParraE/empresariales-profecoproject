package com.profeco.consumer.product;

import com.profeco.consumer.entities.Category;
import com.profeco.consumer.entities.Market;
import com.profeco.consumer.entities.MarketProduct;
import com.profeco.consumer.entities.Product;
import com.profeco.consumer.repositories.ProductRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.yaml.snakeyaml.error.Mark;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void test_findProductsByMarket(){
        Market market = Market.builder().id(1L).build();
//        MarketProduct marketProduct = MarketProduct.builder()
//                .market(market)
//                .price(10d)
//                .build();
//        List<MarketProduct> marketProductList = new ArrayList<>();
//        marketProductList.add(marketProduct);
//
//        Product product = Product.builder()
//                .name("azucar")
//                .description("azucar morena")
//                .category(Category.builder().id(1L).build())
//                .marketProductList(marketProductList)
//                .build();
//
//        Product productCreated = productRepository.save(product);

        List<Product> products = productRepository.findByMarketProductListMarket(market);

        Assertions.assertThat(products.size()).isEqualTo(3);
    }
}
