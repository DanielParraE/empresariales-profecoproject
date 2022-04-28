package com.profeco.consumer.dto;

import com.profeco.consumer.entities.Category;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private CategoryDTO category;
    private List<MarketProductDTO> marketProductList;
    private String image;
    private String status;
}
