package me.parkdaiho.project.config.oauth2;

import me.parkdaiho.project.domain.user.Provider;

import java.util.Map;

public class NaverUserInfo extends OAuth2UserInfo{

    public NaverUserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getProvider() {
        return Provider.NAVER.getProvider();
    }
}
