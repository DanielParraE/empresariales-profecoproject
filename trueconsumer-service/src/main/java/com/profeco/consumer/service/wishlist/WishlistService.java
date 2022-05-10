package com.profeco.consumer.service.wishlist;

import com.profeco.consumer.entities.Consumer;
import com.profeco.consumer.entities.MarketProduct;
import com.profeco.consumer.entities.Wishlist;

import java.util.List;

public interface WishlistService {

    Wishlist addProduct(Long wishlistId, Long product);
    Wishlist removeProduct(Long wishlistId, Long product);

    Wishlist findWishlistById(Long id);
    Wishlist createWishlist(Wishlist wishlist);
    boolean deleteWishlist(Long id);
    List<Wishlist> findByMarket(Long id);
    List<Wishlist> findByConsumer(Long id);
    Wishlist findByConsumerAndMarket(Long consumerId, Long marketId);

    List<Wishlist> findAll();
}
