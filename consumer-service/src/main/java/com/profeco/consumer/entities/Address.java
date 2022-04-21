package com.profeco.consumer.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "addresses")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_code")
    @NotEmpty(message = "post code must not be empty")
    private String postCode;

    @NotEmpty(message = "city must not be empty")
    private String city;

    @NotEmpty(message = "street must not be empty")
    private String street;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Market market;
}
