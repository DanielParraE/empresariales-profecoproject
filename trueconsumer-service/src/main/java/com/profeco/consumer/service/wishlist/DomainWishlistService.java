package com.profeco.consumer.service.wishlist;

import com.profeco.consumer.entities.*;
import com.profeco.consumer.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.error.Mark;

import java.util.List;

@Service
public class DomainWishlistService implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;


    @Override
    public Wishlist addProduct(Long wishlistId, Long productId) {
        Wishlist wishlist = findWishlistById(wishlistId);
        if (wishlist == null) return null;

        Product product = Product.builder().id(productId).build();
        //WishlistItem item = WishlistItem.builder().wishlist(wishlist).product(product).build();
        WishlistItem item = WishlistItem.builder().product(product).build();
        wishlist.getItems().add(item);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist removeProduct(Long wishlistId, Long productId) {
        Wishlist wishlist = findWishlistById(wishlistId);
        if (wishlist == null) return null;

        Product product = Product.builder().id(productId).build();
        //WishlistItem item = WishlistItem.builder().wishlist(wishlist).product(product).build();
        WishlistItem item = WishlistItem.builder().product(product).build();

        List<WishlistItem> items = wishlist.getItems();

        if (!items.contains(item)) return wishlist;

        items.remove(item);

        if (items.isEmpty()) {
            wishlistRepository.delete(wishlist);
            return null;
        }

        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist findWishlistById(Long id) {
        return wishlistRepository.findById(id).orElse(null);
    }

    @Override
    public Wishlist createWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    @Override
    public boolean deleteWishlist(Long id) {
        Wishlist wishlist = findWishlistById(id);
        if (wishlist == null) return false;

        wishlistRepository.delete(wishlist);
        return true;
    }

    @Override
    public List<Wishlist> findByMarket(Long id) {
        return wishlistRepository.findByMarket(Market.builder().id(id).build());
    }

    @Override
    public List<Wishlist> findByConsumer(Long id) {
        return wishlistRepository.findByCreator(Consumer.builder().id(id).build());
    }

    @Override
    public Wishlist findByConsumerAndMarket(Long consumerId, Long marketId) {
        Consumer consumer = Consumer.builder().id(consumerId).build();
        Market market = Market.builder().id(marketId).build();
        return wishlistRepository.findByCreatorAndMarket(consumer, market);
    }

    @Override
    public List<Wishlist> findAll() {
        return wishlistRepository.findAll();
    }
}
