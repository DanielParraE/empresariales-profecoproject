package com.profeco.consumer.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "categories")
@Data  @AllArgsConstructor  @NoArgsConstructor  @Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "name must not be empty")
    private String name;

    @NotEmpty(message = "name must not be empty")
    private String description;
}
