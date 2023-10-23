package org.softuni.mobilele.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "open.exchange.rates")
public class OpenExchangeRateConfig {

  private String appId;
  private List<String> symbols;
  private String host;
  private String schema;
  private String path;
  private boolean enabled;

  public String getPath() {
    return path;
  }

  public OpenExchangeRateConfig setPath(String path) {
    this.path = path;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public OpenExchangeRateConfig setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public List<String> getSymbols() {
    return symbols;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public OpenExchangeRateConfig setEnabled(boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public OpenExchangeRateConfig setSymbols(List<String> symbols) {
    this.symbols = symbols;
    return this;
  }

  public String getHost() {
    return host;
  }

  public OpenExchangeRateConfig setHost(String host) {
    this.host = host;
    return this;
  }

  public String getSchema() {
    return schema;
  }

  public OpenExchangeRateConfig setSchema(String schema) {
    this.schema = schema;
    return this;
  }

  @Override
  public String toString() {
    return "OpenExchangeRateConfig{" +
        "appId='" + appId + '\'' +
        ", symbols=" + symbols +
        ", host='" + host + '\'' +
        ", schema='" + schema + '\'' +
        ", path='" + path + '\'' +
        ", enabled=" + enabled +
        '}';
  }
}
