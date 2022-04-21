package com.profeco.consumer.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rel_wishlistsproducts")
@Data
public class WishlistProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "market_product_id")
    private MarketProduct product;
}
