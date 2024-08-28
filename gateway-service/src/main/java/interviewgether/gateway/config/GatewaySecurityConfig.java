package interviewgether.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.*;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestCustomizers;
import org.springframework.security.oauth2.client.web.server.DefaultServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.authorization.HttpStatusServerAccessDeniedHandler;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {

    private final CookieCsrfWebFilter cookieCsrfWebFilter;
    private final ReactiveClientRegistrationRepository clientRegistrationRepository;

    public GatewaySecurityConfig(CookieCsrfWebFilter cookieCsrfWebFilter, ReactiveClientRegistrationRepository clientRegistrationRepository) {
        this.cookieCsrfWebFilter = cookieCsrfWebFilter;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Bean
    public SecurityWebFilterChain gatewaySecurityWebFilterChain(ServerHttpSecurity http){
        DefaultServerOAuth2AuthorizationRequestResolver pkceResolver =
                new DefaultServerOAuth2AuthorizationRequestResolver(clientRegistrationRepository);
        pkceResolver.setAuthorizationRequestCustomizer(OAuth2AuthorizationRequestCustomizers.withPkce());
        return http
                // ****
                .csrf(csrfSpec -> csrfSpec
                        .csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(new ServerCsrfTokenRequestAttributeHandler())
                )
                .addFilterAfter(cookieCsrfWebFilter, SecurityWebFiltersOrder.CSRF)
                // ****
                .authorizeExchange(authorize -> authorize
                        .pathMatchers("/ui/**", "/auth/**", "/login", "/logout").permitAll()
                        .anyExchange().authenticated()
                )
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(
                                new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED)
                        )
                        .accessDeniedHandler(
                                new HttpStatusServerAccessDeniedHandler(HttpStatus.FORBIDDEN)
                        )
//                        .authenticationEntryPoint(new RedirectServerAuthenticationEntryPoint("/oauth2/authorization/auth-server"))
                )
                .oauth2Login(oAuth2LoginSpec -> oAuth2LoginSpec
                        // Enables use of PKCE
                        .authorizationRequestResolver(pkceResolver)
                        .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/ui/profile"))
                )
                .oidcLogout(spec -> spec
                        .backChannel(Customizer.withDefaults())
                )
                .logout(spec -> spec
                        .logoutUrl("/logout")
                        // allows client initiated logout on the authorization server
                        .logoutSuccessHandler(oidcLogoutSuccessHandler())
                )
                .build();
    }


    // Enables client-initiated logout
    private ServerLogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedServerLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedServerLogoutSuccessHandler(clientRegistrationRepository);
//        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}");
        return oidcLogoutSuccessHandler;
    }
}