package org.softuni.mobilele.service;

import org.softuni.mobilele.model.dto.ReCaptchaResponseDTO;

public interface ReCaptchaService {
  ReCaptchaResponseDTO verify(String response);
}
