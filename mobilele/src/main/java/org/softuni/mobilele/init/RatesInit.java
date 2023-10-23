package org.softuni.mobilele.init;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.softuni.mobilele.config.OpenExchangeRateConfig;
import org.softuni.mobilele.model.dto.ExchangeRatesDTO;
import org.softuni.mobilele.service.CurrencyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RatesInit implements CommandLineRunner {

  private final RestTemplate restTemplate;
  private final OpenExchangeRateConfig openExchangeRateConfig;

  private final CurrencyService currencyService;

  public RatesInit(
      RestTemplate restTemplate,
      OpenExchangeRateConfig openExchangeRateConfig,
      CurrencyService currencyService) {
    this.restTemplate = restTemplate;
    this.openExchangeRateConfig = openExchangeRateConfig;
    this.currencyService = currencyService;
  }

  @Override
  public void run(String... args) throws Exception {
    if (openExchangeRateConfig.isEnabled()) {
      String openExchangeRateUrlTemplate =
          openExchangeRateConfig.getSchema()
              + "://"
              + openExchangeRateConfig.getHost()
              + openExchangeRateConfig.getPath()
              + "?app_id={app_id}&symbols={symbols}";

      Map<String, String> requestParams = Map.of(
          "app_id", openExchangeRateConfig.getAppId(),
          "symbols", String.join(",", openExchangeRateConfig.getSymbols())
      );

      ExchangeRatesDTO exchangeRatesDTO = restTemplate
          .getForObject(openExchangeRateUrlTemplate,
              ExchangeRatesDTO.class,
              requestParams
          );

      currencyService.refreshRates(exchangeRatesDTO);
    }
  }
}
