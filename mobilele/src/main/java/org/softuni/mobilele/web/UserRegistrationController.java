package org.softuni.mobilele.web;

import org.softuni.mobilele.model.dto.ReCaptchaResponseDTO;
import org.softuni.mobilele.model.dto.UserRegistrationDTO;
import org.softuni.mobilele.service.ReCaptchaService;
import org.softuni.mobilele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/users")
@Controller
public class UserRegistrationController {

  private final UserService userService;
  private final ReCaptchaService reCaptchaService;

  public UserRegistrationController(
      UserService userService,
      ReCaptchaService reCaptchaService) {
    this.userService = userService;
    this.reCaptchaService = reCaptchaService;
  }

  @GetMapping("/register")
  public String register() {
    return "auth-register";
  }

  @PostMapping("/register")
  public String register(UserRegistrationDTO userRegistrationDTO,
      @RequestParam("g-recaptcha-response") String reCaptchaResponse) {

    boolean isBot = !reCaptchaService
        .verify(reCaptchaResponse)
        .map(ReCaptchaResponseDTO::isSuccess)
        .orElse(false);

    if (isBot) {
      return "redirect:/";
    }

    userService.registerUser(userRegistrationDTO);

    return "redirect:/";
  }


}
