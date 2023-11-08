package org.softuni.mobilele.web;

import org.softuni.mobilele.model.dto.ConvertRequestDTO;
import org.softuni.mobilele.model.dto.MoneyDTO;
import org.softuni.mobilele.service.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyRestController {

  private final CurrencyService currencyService;

  public CurrencyRestController(CurrencyService currencyService) {
    this.currencyService = currencyService;
  }

  @GetMapping("/api/currency/convert")
  public MoneyDTO convert(ConvertRequestDTO convertRequestDTO) {
    return currencyService.convertBase(convertRequestDTO);
  }

}
