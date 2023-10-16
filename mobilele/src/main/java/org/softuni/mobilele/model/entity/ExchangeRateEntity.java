package org.softuni.mobilele.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class ExchangeRateEntity {

  @Id
  @NotEmpty
  private String currency;

  @NotNull
  private BigDecimal rate;

  public String getCurrency() {
    return currency;
  }

  public ExchangeRateEntity setCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public ExchangeRateEntity setRate(BigDecimal rate) {
    this.rate = rate;
    return this;
  }
}
