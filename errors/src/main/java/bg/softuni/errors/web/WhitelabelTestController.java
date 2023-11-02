package bg.softuni.errors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WhitelabelTestController {

  @GetMapping("/test-whitelabel")
  public String testWhitelaabel() {
    throw new NullPointerException("Something went wrong!");
  }

  @GetMapping("/iae")
  public String testIae() {
    throw new IllegalArgumentException("iae");
  }

}
