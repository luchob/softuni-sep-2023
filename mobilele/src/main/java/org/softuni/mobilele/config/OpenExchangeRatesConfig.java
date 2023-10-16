package org.softuni.mobilele.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "open.exchange.rates")
public class OpenExchangeRatesConfig {

  private String appId;
  private List<String> symbols;

  private String host;
  private String path;

  public String getHost() {
    return host;
  }

  public OpenExchangeRatesConfig setHost(String host) {
    this.host = host;
    return this;
  }

  public String getPath() {
    return path;
  }

  public OpenExchangeRatesConfig setPath(String path) {
    this.path = path;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public OpenExchangeRatesConfig setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public List<String> getSymbols() {
    return symbols;
  }

  public OpenExchangeRatesConfig setSymbols(List<String> symbols) {
    this.symbols = symbols;
    return this;
  }
}
