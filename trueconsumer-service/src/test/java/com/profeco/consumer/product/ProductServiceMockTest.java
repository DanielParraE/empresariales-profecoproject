package com.profeco.consumer.product;

import com.profeco.consumer.entities.Category;
import com.profeco.consumer.entities.Product;
import com.profeco.consumer.repositories.ProductRepository;
import com.profeco.consumer.service.product.DomainProductService;
import com.profeco.consumer.service.product.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        productService = new DomainProductService(productRepository);

        //        MarketProduct marketProduct = MarketProduct.builder()
//                .market(market)
//                .price(10d)
//                .build();
//        List<MarketProduct> marketProductList = new ArrayList<>();
//        marketProductList.add(marketProduct);
//
        Product product = Product.builder()
                .name("azucar")
                .description("azucar morena")
                .category(Category.builder().id(1L).build())
                .build();

        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));
    }

    @Test
    public void test_GetProductById(){
        Product product = productService.getProduct(1L);
        Assertions.assertThat(product.getName()).isEqualTo("azucar");
    }
}
