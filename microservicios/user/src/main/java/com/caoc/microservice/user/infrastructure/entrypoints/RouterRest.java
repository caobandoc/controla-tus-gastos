package com.caoc.microservice.user.infrastructure.entrypoints;

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
                .path("/api/v1/user", builder -> builder
                        .GET("", handler::getAllUsers)
                        .GET("/{id}", handler::getUserByEmail)
                        .POST("", handler::saveUser))
                .build();
    }
}
