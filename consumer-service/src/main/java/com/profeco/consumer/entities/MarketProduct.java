package com.profeco.consumer.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "rel_marketsproducts")
@Data
public class MarketProduct {
    @Id
    @Column(name = "marketproduct_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id", nullable = false)
    private Market market;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @JsonManagedReference
    @OneToMany(mappedBy = "marketProduct", cascade = CascadeType.ALL)
    private List<ProductReview> reviews;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<WishlistProduct> wishlistProducts;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Inconsistency> inconsistencies;

    @Positive(message = "price must be > 0")
    private Double price;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
