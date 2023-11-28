package com.caoc.gatewayserver.applications.config;

import com.caoc.gatewayserver.drivenadapter.restconsumer.RestConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final RestConsumer restConsumer;

    @Autowired
    public AuthFilter(RestConsumer restConsumer){
        super(Config.class);
        this.restConsumer = restConsumer;
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            //filtrar POST /api/v1/user para crear usuario
            if (isPostToUserEndpoint(exchange))
                return chain.filter(exchange);

            //filtrar si no hay token
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                return onError(exchange, HttpStatus.BAD_REQUEST);

            String tokenHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
            String [] chunks = tokenHeader.split(" ");
            if (chunks.length < 2 || !chunks[0].equals("Bearer"))
                return onError(exchange, HttpStatus.BAD_REQUEST);

            return restConsumer.validateToken(chunks[1])
                    .map(t -> exchange)
                    .flatMap(chain::filter)
                    .onErrorResume(ex -> onError(exchange, HttpStatus.UNAUTHORIZED));
        };
    }

    private boolean isPostToUserEndpoint(ServerWebExchange exchange) {
        return exchange.getRequest().getMethod() == HttpMethod.POST
                && exchange.getRequest().getURI().getPath().equals("/api/v1/user");
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus status){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }

    public static class Config{}
}
