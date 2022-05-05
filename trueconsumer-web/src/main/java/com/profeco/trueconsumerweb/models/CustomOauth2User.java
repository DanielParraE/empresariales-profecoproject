package com.profeco.trueconsumerweb.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOauth2User implements OAuth2User {

    private OAuth2User oAuth2User;
    private String authenticationType;
    private String picture;
    private String surname;
    private String rfc;

    public CustomOauth2User(OAuth2User oAuth2User, String authenticationType){
        this.oAuth2User = oAuth2User;
        this.authenticationType = authenticationType;
    }

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oAuth2User.getAttribute("name");
    }

    public String getEmail() {
        return oAuth2User.getAttribute("email");
    }

    public String getAuthenticationType() {
        return this.authenticationType;
    }

    public String getPicture(){
        return oAuth2User.getAttribute("picture");
    }

    public String getSurname(){
        return oAuth2User.getAttribute("family_name");
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
}
