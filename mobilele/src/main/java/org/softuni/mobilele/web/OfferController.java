package org.softuni.mobilele.web;


import jakarta.validation.Valid;
import org.softuni.mobilele.model.dto.CreateOfferDTO;
import org.softuni.mobilele.model.enums.EngineEnum;
import org.softuni.mobilele.service.ModelService;
import org.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/offer")
public class OfferController {

  private final OfferService offerService;
  private final ModelService modelService;

  public OfferController(OfferService offerService,
                         ModelService modelService) {
    this.offerService = offerService;
    this.modelService = modelService;
  }

  @ModelAttribute("engines")
  public EngineEnum[] engines() {
    return EngineEnum.values();
  }

  @GetMapping("/add")
  public String add(Model model) {

    if (!model.containsAttribute("createOfferDTO")) {
      model.addAttribute("createOfferDTO", CreateOfferDTO.empty());
    }

    model.addAttribute("brandDTOs", modelService.getAllGroupedByBrand());

    return "offer-add";
  }

  @PostMapping("/add")
  public String add(
      @Valid CreateOfferDTO createOfferDTO,
      BindingResult bindingResult,
      RedirectAttributes rAtt) {

    if(bindingResult.hasErrors()){
      rAtt.addFlashAttribute("createOfferDTO", createOfferDTO);
      rAtt.addFlashAttribute("org.springframework.validation.BindingResult.createOfferDTO", bindingResult);
      return "redirect:/offer/add";
    }


    UUID newOfferUUID = offerService.createOffer(createOfferDTO);

    return "redirect:/offer/" + newOfferUUID;
  }

  @GetMapping("/{uuid}")
  public String details(@PathVariable("uuid") UUID uuid) {
    return "details";
  }
}
