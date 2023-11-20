package org.softuni.mobilele.service.impl;

import java.net.URI;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softuni.mobilele.config.ReCaptchaConfig;
import org.softuni.mobilele.model.dto.ReCaptchaResponseDTO;
import org.softuni.mobilele.service.ReCaptchaService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

@Service
public class ReCaptchaServiceImpl implements ReCaptchaService {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(ReCaptchaServiceImpl.class);
  private final WebClient webClient;
  private final ReCaptchaConfig reCaptchaConfig;

  public ReCaptchaServiceImpl(
      WebClient webClient,
      ReCaptchaConfig reCaptchaConfig) {
    this.webClient = webClient;
    this.reCaptchaConfig = reCaptchaConfig;
  }

  @Override
  public Optional<ReCaptchaResponseDTO> verify(String token) {
    ReCaptchaResponseDTO response = webClient
        .post()
        .uri(this::buildRecaptchaURI)
        .body(BodyInserters
            .fromFormData("secret", reCaptchaConfig.getSecret())
            .with("response", token))
        .retrieve()
        .bodyToMono(ReCaptchaResponseDTO.class)
        .doOnError(t -> LOGGER.error("Error fetching google reponse...", t))
        .onErrorComplete()
        .block();

    return Optional.ofNullable(response);
  }

  private URI buildRecaptchaURI(UriBuilder uriBuilder) {
    // REST endpoint for google verification.
    // https://www.google.com/recaptcha/api/siteverify
    return uriBuilder
        .scheme("https")
        .host("www.google.com")
        .path("/recaptcha/api/siteverify")
        .build();
  }
}
