package com.profeco.consumer.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity
@Table(name = "wishlists")
@Data  @AllArgsConstructor  @NoArgsConstructor  @Builder
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference(value = "consumer-wishlist")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id")
    private Consumer creator;

    @JsonManagedReference(value = "wishlist-wishlistproduct")
    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL)
    private List<WishlistProduct> wishlistProducts;

}
