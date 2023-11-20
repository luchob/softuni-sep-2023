package org.softuni.mobilele.service;

import java.util.Optional;
import org.softuni.mobilele.model.dto.ReCaptchaResponseDTO;

public interface ReCaptchaService {
  Optional<ReCaptchaResponseDTO> verify(String token);
}
