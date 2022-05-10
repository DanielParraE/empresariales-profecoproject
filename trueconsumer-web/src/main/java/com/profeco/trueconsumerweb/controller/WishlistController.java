package com.profeco.trueconsumerweb.controller;

import com.profeco.trueconsumerweb.models.CustomOauth2User;
import com.profeco.trueconsumerweb.models.Person;
import com.profeco.trueconsumerweb.models.Wishlist;
import com.profeco.trueconsumerweb.repository.PersonRepository;
import com.profeco.trueconsumerweb.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(value = "/wishlists")
    public String  getWishlistByConsumer(@AuthenticationPrincipal OAuth2User principal, Model model){
        String uid = (principal == null )?
                ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
                :((CustomOauth2User) principal).getEmail();

        Person person = personRepository.findByUID(uid);

        Wishlist[] wishlists = wishlistService.getWishlistByConsumer(Long.valueOf(person.getConsumerId()));
        model.addAttribute("wishlists", wishlists);
        return "wishlists";
    }
}
