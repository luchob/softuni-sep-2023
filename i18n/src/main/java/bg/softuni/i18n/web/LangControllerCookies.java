package bg.softuni.i18n.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LangControllerCookies {


  @GetMapping("/cookies")
  public String langCookies(
      @CookieValue(
          value = "lang",
          defaultValue = "bg") String lang,
      Model model) {

    model.addAttribute("lang", lang);

    return "lang";
  }

  @PostMapping("/cookies")
  public String langCookies(@RequestParam("lang") String lang,
      HttpServletResponse response) {

    Cookie cookie = new Cookie("lang", lang);
    response.addCookie(cookie);

    return "redirect:/cookies";
  }

}
