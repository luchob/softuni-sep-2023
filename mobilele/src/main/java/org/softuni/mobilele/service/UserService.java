package org.softuni.mobilele.service;

import org.softuni.mobilele.model.dto.UserRegistrationDTO;
import org.springframework.security.core.Authentication;

public interface UserService {

  void registerUser(UserRegistrationDTO userRegistrationDTO);

  void createUserIfNotExist(String email, String names);

  Authentication login(String email);
}
