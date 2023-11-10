package org.softuni.mobilele.web;

import org.softuni.mobilele.model.dto.OfferSummaryDTO;
import org.softuni.mobilele.service.MonitoringService;
import org.softuni.mobilele.service.OfferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OffersController {

  private final MonitoringService monitoringService;
  private final OfferService offerService;

  public OffersController(MonitoringService monitoringService,
      OfferService offerService) {
    this.monitoringService = monitoringService;
    this.offerService = offerService;
  }

  @GetMapping("/all")
  public String all(Model model,
      @PageableDefault(
          size = 3,
          sort = "uuid"
      ) Pageable pageable) {

    monitoringService.incOfferSearches();

    Page<OfferSummaryDTO> allOffers = offerService.getAllOffers(pageable);

    model.addAttribute("offers", allOffers);

    return "offers";
  }

}
