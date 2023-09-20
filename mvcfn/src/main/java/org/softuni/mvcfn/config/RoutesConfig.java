package org.softuni.mvcfn.config;

import static org.springframework.web.servlet.function.RouterFunctions.route;

import org.softuni.mvcfn.web.TestHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class RoutesConfig {

  @Bean
  public RouterFunction<ServerResponse> routingFunction(TestHandlers testHandlers) {
    return route()
        .GET("/test", testHandlers::test)
        .build();
  }

}
