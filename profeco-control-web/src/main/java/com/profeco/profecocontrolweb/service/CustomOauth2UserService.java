package com.profeco.profecocontrolweb.service;

import com.profeco.profecocontrolweb.model.CustomOauth2User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        String authenticationType = userRequest.getClientRegistration().getClientName();
        return new CustomOauth2User(super.loadUser(userRequest), authenticationType);
    }
}
