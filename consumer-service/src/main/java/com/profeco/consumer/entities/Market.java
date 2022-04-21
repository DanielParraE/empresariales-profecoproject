package com.profeco.consumer.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity
@Table(name = "markets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String rfc;

    @Valid
    @JsonManagedReference
    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private List<Address> addressList;

    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private List<MarketReview> reviews;

    @JsonManagedReference
    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private List<MarketProduct> marketProductList;
}
