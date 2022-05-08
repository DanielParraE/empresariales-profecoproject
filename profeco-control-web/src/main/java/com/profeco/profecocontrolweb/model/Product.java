package com.profeco.profecocontrolweb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Product implements Serializable {
    private Long id;
    private  String name;
    private String description;

    private String image;

    private List<Inconsistency> inconsistencyList;
}
