package com.profeco.consumer.dto;

import com.profeco.consumer.entities.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductList {
    private List<Product> products;
}
