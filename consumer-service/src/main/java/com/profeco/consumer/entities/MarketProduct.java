package com.profeco.consumer.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.metadata.ValidateUnwrappedValue;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "rel_marketsproducts")
@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class MarketProduct {
    @Id
    @Column(name = "marketproduct_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference(value = "market-marketproduct")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id", nullable = false)
    private Market market;

    //@JsonBackReference(value = "product-marketproduct")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @JsonManagedReference(value = "market-product-productreview")
    @OneToMany(mappedBy = "marketProduct", cascade = CascadeType.ALL)
    private List<ProductReview> reviews;

    @JsonManagedReference(value = "market-product-wishlist-product")
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<WishlistProduct> wishlistProducts;

    @JsonManagedReference(value = "market-product-inconsistency")
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Inconsistency> inconsistencies;

    @Positive(message = "price must be > 0")
    private Double price;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
