package bg.softuni.errors.web;

import bg.softuni.errors.exception.ObjectNotFoundException;
import bg.softuni.errors.exception.ObjectNotFoundRestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestRestController {

  @GetMapping("/test-rest")
  public ResponseEntity<Student> get() {
    throw new ObjectNotFoundRestException("Student not found");
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ObjectNotFoundRestException.class)
  public ErrorInfo handleNotFound(ObjectNotFoundRestException exception) {
    return new ErrorInfo(
        "/test-rest",
        exception
    );
  }

}

record Student(String name, int age){}

class ErrorInfo {
  public final String url;
  public final String ex;
  public ErrorInfo(String url, Exception ex) {
    this.url = url;
    this.ex = ex.getMessage();
  }
}

