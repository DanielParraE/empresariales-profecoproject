package com.profeco.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "rel_marketsproducts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarketProduct implements Serializable {

    @Id
    @Column(name = "marketproduct_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id", nullable = false)
    private Market market;

//    @JsonBackReference
//    @ManyToOne
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product;

    private float price;
}
