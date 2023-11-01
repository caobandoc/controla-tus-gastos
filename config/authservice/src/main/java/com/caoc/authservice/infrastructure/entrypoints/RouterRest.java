package com.caoc.authservice.infrastructure.entrypoints;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route()
                .path("/api/v1/auth", builder -> builder
                        .POST("/login", handler::login)
                        .POST("/validate", handler::validate))
                .build();
    }
}
