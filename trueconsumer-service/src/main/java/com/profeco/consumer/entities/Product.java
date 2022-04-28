package com.profeco.consumer.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.profeco.consumer.dto.CategoryDTO;
import com.profeco.consumer.dto.MarketDTO;
import com.profeco.consumer.dto.MarketProductDTO;
import com.profeco.consumer.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data  @AllArgsConstructor  @NoArgsConstructor  @Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name must not be empty")
    private String name;

    @NotEmpty(message = "Name must not be empty")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonManagedReference(value = "product-marketproduct")
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<MarketProduct> marketProductList;

    private String image;

    private String status;

    public ProductDTO toDTO(){
        List<MarketProductDTO> marketProductDTOList = new ArrayList<>();
        if (!this.getMarketProductList().isEmpty()){
          for (MarketProduct marketProduct: marketProductList){
              MarketProductDTO.builder()
                      .id(marketProduct.getMarket().getId())
                      .price(marketProduct.getPrice())
                      .market(MarketDTO.builder()
                              .id(marketProduct.getMarket().getId())
                              .name(marketProduct.getMarket().getName())
                              .image(marketProduct.getMarket().getImage())
                              .rfc(marketProduct.getMarket().getRfc())
                              .build())
                      .build();
          }
        }

        return ProductDTO.builder()
                .name(name)
                .description(description)
                .category(CategoryDTO.builder()
                        .name(category.getName()).build())
                .image(image)
                .status(status)
                .marketProductList(marketProductDTOList)
                .build();
    }
}
