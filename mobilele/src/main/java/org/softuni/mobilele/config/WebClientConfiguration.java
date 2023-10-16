package org.softuni.mobilele.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfiguration {

  @Bean
  public WebClient webClient() {
    return WebClient.builder()
        .baseUrl("http://localhost:8080")
        .filter(logRequest())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  private static ExchangeFilterFunction logRequest() {
    return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
      System.out.println("Request: " + clientRequest.method() + " " + clientRequest.url());
      return Mono.just(clientRequest);
    });
  }
}
