package interviewgether.gateway.controller;


import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerRequest;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/user")
    public Mono<ResponseEntity<?>> getUser(@AuthenticationPrincipal OAuth2User user) {
        if (user == null) {
            return Mono.just(new ResponseEntity<>(HttpStatus.OK));
        } else {
            return Mono.just(new ResponseEntity<>(user.getAttributes(), HttpStatus.OK));
        }
    }

    @GetMapping("/status")
    public Mono<ResponseEntity<?>> getAuthenticationStatus(Principal principal){
        return Mono.just(new ResponseEntity<>(principal != null, HttpStatus.OK));
    }


}
