package com.profeco.trueconsumerweb.models;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor @NoArgsConstructor
@Data
public class Product implements Serializable {
    private long id;
    private String name;
    private String description;
    private Category category;
    private String image;
    private String status;
}
