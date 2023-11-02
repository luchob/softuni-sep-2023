package bg.softuni.errors.web;

import bg.softuni.errors.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NotFound1Controller {

  @GetMapping("/not-found1/{id}")
  public String notFound1(@PathVariable("id") String id) {
    throw new ObjectNotFoundException("Object with id " + id + " was not found!", id);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ObjectNotFoundException.class)
  public ModelAndView handleNotFound(ObjectNotFoundException exception) {
    ModelAndView modelAndView = new ModelAndView("notfound1");
    modelAndView.addObject("id", exception.getId());
    return modelAndView;
  }
}
