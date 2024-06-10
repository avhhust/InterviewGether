package com.interviewgether.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.util.Objects;

@Embeddable
public class SocialNetwork {
    @NotBlank
    private String socialNetworkName;

    @Column
    private String nickname;

    @NotBlank
    @URL
    @Column(name = "social_url")
    private String socialURL;

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

    public String getSocialURL() {
        return socialURL;
    }

    public void setSocialURL(String socialURL) {
        this.socialURL = socialURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialNetwork that = (SocialNetwork) o;
        return Objects.equals(socialNetworkName, that.socialNetworkName) && Objects.equals(nickname, that.nickname) && Objects.equals(socialURL, that.socialURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socialNetworkName, nickname, socialURL);
    }
}