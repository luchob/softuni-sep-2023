package org.softuni.mobilele.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google.recaptcha.key")
public class ReCaptchaConfig {

  private String site;
  private String secret;

  public String getSite() {
    return site;
  }

  public ReCaptchaConfig setSite(String site) {
    this.site = site;
    return this;
  }

  public String getSecret() {
    return secret;
  }

  public ReCaptchaConfig setSecret(String secret) {
    this.secret = secret;
    return this;
  }
}