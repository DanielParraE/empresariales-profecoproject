package com.profeco.truemarketweb;

import com.profeco.truemarketweb.models.CustomOauth2User;
import com.profeco.truemarketweb.models.Market;
import com.profeco.truemarketweb.models.Person;
import com.profeco.truemarketweb.repository.PersonRepository;
import com.profeco.truemarketweb.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MarketService marketService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        CustomOauth2User oauth2User = (CustomOauth2User) authentication.getPrincipal();

        Person personLogin  = personRepository.findByUID(oauth2User.getEmail());
        // new user
        if (personLogin == null){

            // save image
            BufferedInputStream in = new BufferedInputStream(new URL(oauth2User.getPicture()).openStream());
            byte[] bytes = in.readAllBytes();

            String surname = oauth2User.getSurname() == null ? "" : oauth2User.getSurname();

            //save market
            Market consumer = Market.builder()
                    .email(oauth2User.getEmail())
                    .name(oauth2User.getName())
                    .corpType(oauth2User.getSurname()).build();

            Market marketCreated = marketService.postMarket(consumer, bytes, oauth2User.getAttribute("sub").toString() + ".png");

            Person personLdap = Person.builder()
                    .userId(oauth2User.getEmail())
                    .fullName(oauth2User.getName())
                    .lastName(surname)
                    .authenticationType(oauth2User.getAuthenticationType())
                    .marketId(marketCreated.getId().toString())
                    .password("")
                    .build();

            personRepository.create(personLdap);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
