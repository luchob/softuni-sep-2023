package org.softuni.mobilele.web;


import jakarta.validation.Valid;
import java.util.UUID;
import org.softuni.mobilele.model.dto.CreateOfferDTO;
import org.softuni.mobilele.model.enums.EngineEnum;
import org.softuni.mobilele.service.BrandService;
import org.softuni.mobilele.service.OfferService;
import org.softuni.mobilele.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offer")
public class OfferController {

  private final OfferService offerService;
  private final BrandService brandService;

  public OfferController(OfferService offerService,
      BrandService brandService) {
    this.offerService = offerService;
    this.brandService = brandService;
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

    model.addAttribute("brands", brandService.getAllBrands());

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
    throw new ObjectNotFoundException("Offer not found.", uuid.toString());
  }
}
