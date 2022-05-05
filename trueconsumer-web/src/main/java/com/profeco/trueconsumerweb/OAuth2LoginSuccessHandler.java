package com.profeco.trueconsumerweb;

import com.profeco.trueconsumerweb.models.Consumer;
import com.profeco.trueconsumerweb.models.CustomOauth2User;
import com.profeco.trueconsumerweb.models.Person;
import com.profeco.trueconsumerweb.repository.PersonRepository;
import com.profeco.trueconsumerweb.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    private ConsumerService consumerService;

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

            //save consumer
            Consumer consumer = Consumer.builder()
                    .rfc("ABCD420313AP1")
                    .email(oauth2User.getEmail())
                    .fullName(oauth2User.getName()).build();

            String surname = oauth2User.getSurname() == null ? "" : oauth2User.getSurname();
            System.out.println(oauth2User.getAttributes());
            Consumer consumerCreated = consumerService.postConsumer(consumer, bytes, oauth2User.getAttribute("sub").toString());

            Person personLdap = Person.builder()
                    .userId(oauth2User.getEmail())
                    .fullName(oauth2User.getName())
                    .lastName(surname)
                    .authenticationType(oauth2User.getAuthenticationType())
                    .consumerId(consumerCreated.getId().toString())
                    .password("")
                    .build();

            personRepository.create(personLdap);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
