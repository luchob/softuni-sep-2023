package org.softuni.mobilele.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

  @GetMapping("/users/login")
  public String login() {
    return "auth-login";
  }

  @PostMapping("/users/login-error")
  public String onFailure(
      @ModelAttribute("email") String email,
      Model model) {

    model.addAttribute("email", email);
    model.addAttribute("bad_credentials", "true");

    return "auth-login";
  }
}
