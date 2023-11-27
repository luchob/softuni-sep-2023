package org.softuni.mobilele.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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

  @Operation(summary = "Converts BGN to a target currency.")
  @ApiResponses(value = {
     @ApiResponse(
         responseCode = "200",
         description = "Returned when we successfully converted the currency.",
         content = {
             @Content(
                 mediaType = "application/json",
                 schema = @Schema(implementation = MoneyDTO.class)
             )
        }
     ),
     @ApiResponse(
         responseCode = "404",
         description = "There is no information about this currency",
         content = @Content
     )
  })
  @Parameter(name = "target", description = "The targer currency", required = true)
  @Parameter(name = "amount", description = "The amount to be converted", required = true)
  @GetMapping("/api/currency/convert")
  public MoneyDTO convert(@Valid ConvertRequestDTO convertRequestDTO) {
    return currencyService.convert(convertRequestDTO);
  }

}
