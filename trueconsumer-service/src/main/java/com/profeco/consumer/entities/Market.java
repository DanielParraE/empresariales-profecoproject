package com.profeco.consumer.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String image;

    @Column(name = "web_page")
    private String webPage;

    @JsonManagedReference(value = "market-address")
    @OneToMany(mappedBy = "branchOffice", cascade = CascadeType.ALL)
    private List<Address> addressList;

    //@JsonIgnore
    //@JsonManagedReference(value = "market-reviews")
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<MarketReview> reviews;

    @JsonIgnore
    //@JsonManagedReference(value = "market-marketproduct")
    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private List<MarketProduct> marketProductList;

    private String status;
}
