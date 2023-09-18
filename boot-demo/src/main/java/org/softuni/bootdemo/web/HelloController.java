package org.softuni.bootdemo.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {


  private final String greeting;

  public HelloController(@Value("${demo.greeting.message}") String greeting) {
    this.greeting = greeting;
  }

  @GetMapping("/test")
  public String test(Model model) {
    model.addAttribute("greeting", greeting);
    return "test";
  }

}
