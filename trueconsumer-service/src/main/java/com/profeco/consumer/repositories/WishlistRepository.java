package com.profeco.consumer.repositories;

import com.profeco.consumer.entities.Consumer;
import com.profeco.consumer.entities.Market;
import com.profeco.consumer.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByMarket(Market market);
    List<Wishlist> findByCreator(Consumer consumer);
    Wishlist findByCreatorAndMarket(Consumer consumer, Market market);
}
