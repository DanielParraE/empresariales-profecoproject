package com.profeco.consumer.controller;

import com.profeco.consumer.entities.Wishlist;
import com.profeco.consumer.service.wishlist.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @GetMapping
    public ResponseEntity<List<Wishlist>> findWishlists(@RequestParam(required = false) Long marketId,
                                                        @RequestParam(required = false) Long consumerId){

        List<Wishlist> wishlistList;
        if (marketId == null && consumerId == null)
            wishlistList = wishlistService.findAll();
        else if (marketId != null && consumerId !=null)
            wishlistList = Arrays.asList(wishlistService.findByConsumerAndMarket(consumerId, marketId));
        else {
            wishlistList = (marketId == null) ?
                    wishlistService.findByConsumer(consumerId):
                    wishlistService.findByMarket(marketId);
        }

        if (wishlistList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(wishlistList);
    }

    @PostMapping(value = "/{id}/products/{productId}")
    public String addProduct(){
        return "";
    }

    @DeleteMapping(value = "/{id}/products/{productId}")
    public String removeProduct(){
        return "";
    }
}
