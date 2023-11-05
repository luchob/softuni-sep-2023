package org.softuni.mobilele.web;

import org.softuni.mobilele.model.dto.OfferSummaryDTO;
import org.softuni.mobilele.model.dto.SearchOfferDTO;
import org.softuni.mobilele.service.OfferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OffersController {

  private final OfferService offerService;

  public OffersController(OfferService offerService) {
    this.offerService = offerService;
  }

  @ModelAttribute("searchOfferModel")
  public SearchOfferDTO searchOfferModel() {
    return new SearchOfferDTO();
  }

  @GetMapping("/all")
  public String all(Model model,
      @PageableDefault(
          size = 3,
          sort = "uuid"
      ) Pageable pageable,
      SearchOfferDTO searchOfferDTO) {

    Page<OfferSummaryDTO> allOffers = offerService.getAllOffers(searchOfferDTO, pageable);

    model.addAttribute("searchOfferModel", searchOfferDTO);
    model.addAttribute("offers", allOffers);

    return "offers";
  }

}
