package com.caoc.accountservice.infrastructure.entrypoints.reactiveweb;

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
                        .GET("",handler::getListCatalog)
                        .GET("/{catalog}",handler::getCatalog)
                        .GET("/id/{id}",handler::getCatalogById)
                        .POST("", handler::createCatalog)
                        .DELETE("/{id}",handler::deleteAccount)
                )
                .build();
    }
}
