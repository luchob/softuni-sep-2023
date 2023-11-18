package org.softuni.mobilele.service.impl;

import java.net.URI;
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
public class ReCaptchServiceImpl implements ReCaptchaService {
    private final WebClient webClient;
  private final ReCaptchaConfig reCaptchaConfig;
  private final Logger LOGGER = LoggerFactory.getLogger(ReCaptchServiceImpl.class);

    public ReCaptchServiceImpl(WebClient webClient,
        ReCaptchaConfig reCaptchaConfig) {
      this.webClient = webClient;
      this.reCaptchaConfig = reCaptchaConfig;
    }

    public ReCaptchaResponseDTO verify(String response) {

      return webClient
          .post()
          .uri(this::buildReCaptchRequestURI)
          .body(
              BodyInserters
                  .fromFormData("secret", reCaptchaConfig.getSecret())
                  .with("response", response)
          )
          .retrieve()
          .bodyToMono(ReCaptchaResponseDTO.class)
          .doOnError(t -> LOGGER.error("Error while sending to the recaptcha service.", t))
          .onErrorComplete()
          .block();
    }

    private URI buildReCaptchRequestURI(UriBuilder uriBuilder) {
      https://www.google.com/recaptcha/api/siteverify
      return uriBuilder
          .scheme("https")
          .host("www.google.com")
          .path("/recaptcha/api/siteverify")
          .build();
    }
}
