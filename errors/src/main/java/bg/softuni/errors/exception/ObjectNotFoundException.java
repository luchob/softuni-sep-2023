package bg.softuni.errors.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException{

  private final String id;

  public ObjectNotFoundException(String message, String id) {
      super(message);
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
