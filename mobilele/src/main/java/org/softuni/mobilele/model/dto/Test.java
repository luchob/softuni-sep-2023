package org.softuni.mobilele.model.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.stream.Collectors;
import org.softuni.mobilele.config.OpenExchangeRatesConfig;
import org.softuni.mobilele.repository.ExchangeRateRepository;
import org.softuni.mobilele.service.CurrencyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

//@Component
public class Test implements CommandLineRunner {

  private final ObjectMapper objectMapper;
  private final CurrencyService currencyService;
  private final RestTemplate restTemplate;
  private final WebClient webClient;
  private final OpenExchangeRatesConfig openExchangeRatesConfig;


  public Test(ObjectMapper objectMapper,
      CurrencyService currencyService,
      RestTemplate restTemplate,
      WebClient webClient,
      OpenExchangeRatesConfig openExchangeRatesConfig) {
    this.objectMapper = objectMapper;
    this.currencyService = currencyService;
    this.restTemplate = restTemplate;
    this.webClient = webClient;
    this.openExchangeRatesConfig = openExchangeRatesConfig;
  }

  @Override
  public void run(String... args) throws Exception {
//    String json = """
//        {
//          "disclaimer": "Usage subject to terms: https://openexchangerates.org/terms",
//          "license": "https://openexchangerates.org/license",
//          "timestamp": 1696953600,
//          "base": "USD",
//          "rates": {
//            "BGN": 1.845,
//            "EUR": 0.941894
//          }
//        }
//        """;
//
//    ExchangeRatesDTO exchangeRatesDTO = objectMapper.readValue(json, ExchangeRatesDTO.class);
//
//    currencyService.processExRates(exchangeRatesDTO);

//    Mono<ExchangeRatesDTO> response = webClient
//        .get()
//        .uri(uriBuilder ->
//            uriBuilder
//                .scheme("https")
//                .host(openExchangeRatesConfig.getHost())
//                .path(openExchangeRatesConfig.getPath())
//                .port(443)
//                .queryParam("app_id", openExchangeRatesConfig.getAppId())
//                .queryParam("symbols", String.join(",", openExchangeRatesConfig.getSymbols()))
//                .build())
//        .exchangeToMono(clientResponse -> {
//          if (clientResponse.statusCode() == HttpStatus.OK) {
//            return clientResponse.bodyToMono(ExchangeRatesDTO.class);
//          } else {
//            return clientResponse.createException().flatMap(Mono::error);
//          }
//        })
//        .doOnError(Throwable::printStackTrace);
//
//    ExchangeRatesDTO exchangeRatesDTO = response.block();

    String openExchangeRateURL =
        new StringBuilder()
            .append("https://")
                .append(openExchangeRatesConfig.getHost())
                .append(openExchangeRatesConfig.getPath())
                .append("?app_id={app_id}")
                .append("&symbols={symbols}")
              .toString();

    Map<String, String> requestParams = Map.of(
        "app_id", openExchangeRatesConfig.getAppId(),
        "symbols", String.join(",", openExchangeRatesConfig.getSymbols())
    );

    var exchangeRatesDTO = restTemplate.
        getForObject(openExchangeRateURL, ExchangeRatesDTO.class, requestParams);

    System.out.println(exchangeRatesDTO);
  }
}
