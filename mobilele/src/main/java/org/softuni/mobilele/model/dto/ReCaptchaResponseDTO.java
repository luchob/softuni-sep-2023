package org.softuni.mobilele.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class ReCaptchaResponseDTO {

  private boolean success;
  private List<String> errorCodes;

  public boolean isSuccess() {
    return success;
  }

  public ReCaptchaResponseDTO setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public List<String> getErrorCodes() {
    return errorCodes;
  }

  public ReCaptchaResponseDTO setErrorCodes(List<String> errorCodes) {
    this.errorCodes = errorCodes;
    return this;
  }
}
