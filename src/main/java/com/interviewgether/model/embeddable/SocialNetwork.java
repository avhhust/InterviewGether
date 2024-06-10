package com.interviewgether.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.util.Objects;

@Embeddable
public class SocialNetwork {
    @NotBlank
    @Column(nullable = false)
    private String socialNetworkName;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String nickname;

    @URL
    @Column(name = "social_profile_url", unique = true)
    private String socialProfileURL;

    public SocialNetwork() {
    }

    public String getSocialNetworkName() {
        return socialNetworkName;
    }

    public void setSocialNetworkName(String socialNetworkName) {
        this.socialNetworkName = socialNetworkName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSocialProfileURL() {
        return socialProfileURL;
    }

    public void setSocialProfileURL(String socialURL) {
        this.socialProfileURL = socialURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialNetwork that = (SocialNetwork) o;
        return Objects.equals(socialNetworkName, that.socialNetworkName) && Objects.equals(nickname, that.nickname) && Objects.equals(socialProfileURL, that.socialProfileURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socialNetworkName, nickname, socialProfileURL);
    }
}