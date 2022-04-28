package com.profeco.consumer.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDTO implements Serializable {
    private String name;
}
