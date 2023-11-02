package org.softuni.mobilele.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {
  public ObjectNotFoundException(String message, String id) {
    super("Message: " + message + " Id: " + id);
  }
}
