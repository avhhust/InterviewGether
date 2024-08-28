package com.interviewgether.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/profile")
    public String index(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Hello,%s %s!", jwt.getClaims(), jwt.getSubject());
    }

    @GetMapping("/")
    public String home(){
        return "You have successfully reached resource server";
    }
}
