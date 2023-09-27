package org.softuni.mobilele.web;

import org.softuni.mobilele.model.dto.UserLoginDTO;
import org.softuni.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

  private final UserService userService;

  public UserLoginController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users/login")
  public String login() {
    return "auth-login";
  }

  @GetMapping("/users/logout")
  public String logout() {

    userService.logoutUser();

    return "index";
  }

  @PostMapping("/users/login")
  public String login(UserLoginDTO userLoginDTO) {
    boolean loginSuccessful = userService.loginUser(userLoginDTO);

    return loginSuccessful ? "index" : "auth-login";
  }
}
