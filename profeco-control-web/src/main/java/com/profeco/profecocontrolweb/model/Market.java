package com.profeco.profecocontrolweb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data  @AllArgsConstructor  @NoArgsConstructor @Builder
public class Market implements Serializable {
    private Long id;
    private String name;
    private String rfc;
    private String webPage;
    private String image;
    private List<Product> productList;
}
