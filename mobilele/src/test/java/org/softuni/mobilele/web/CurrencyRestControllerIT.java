package org.softuni.mobilele.web;

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.softuni.mobilele.model.entity.ExchangeRateEntity;
import org.softuni.mobilele.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyRestControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ExchangeRateRepository exchangeRateRepository;

  @BeforeEach
  void setUp() {
    exchangeRateRepository.save(new ExchangeRateEntity().setCurrency("EUR").setRate(BigDecimal.valueOf(0.51)));
    exchangeRateRepository.save(new ExchangeRateEntity().setCurrency("USD").setRate(BigDecimal.valueOf(0.54)));
  }

  @AfterEach
  void tearDown() {
    exchangeRateRepository.deleteAll();
  }

  @Test
  void testCurrencyNotFound() throws Exception {
    mockMvc.perform(
        get("/api/currency/convert").
            param("target", "RSD").
            param("amount", "42")
        ).
        andExpect(status().is4xxClientError());
  }

  @Test
  void testEUR() throws Exception {
    mockMvc.perform(
            get("/api/currency/convert").
                param("target", "EUR").
                param("amount", "50")
        ).
        andExpect(status().is2xxSuccessful()).
        andExpect(jsonPath("$.currency", is("EUR"))).
        andExpect(jsonPath("$.amount", is(25.5)));
  }

}
