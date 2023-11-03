package com.caoc.authservice.infrastructure.entrypoints;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route()
                .path(contextPath, builder -> builder
                        .POST("/login", handler::login)
                        .GET("/validate", handler::validate))
                .build();
    }
}
