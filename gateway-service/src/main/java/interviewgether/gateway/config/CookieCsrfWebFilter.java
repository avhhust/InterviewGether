package interviewgether.gateway.config;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class CookieCsrfWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        Mono<CsrfToken> csrfToken = exchange.getAttribute(CsrfToken.class.getName());
        if(csrfToken != null){
            return csrfToken.doOnSuccess(token -> {
               exchange.getResponse().beforeCommit(() -> {
                   exchange.getResponse()
                           .getHeaders()
                           .add("X-CSRF-TOKEN", token.getToken());
                   return Mono.empty();
               });
            }).then(chain.filter(exchange));
        }
        return chain.filter(exchange);
    }
}
