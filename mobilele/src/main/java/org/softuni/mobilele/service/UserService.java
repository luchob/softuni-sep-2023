package org.softuni.mobilele.service;

import org.softuni.mobilele.model.dto.UserRegistrationDTO;

public interface UserService {

  void registerUser(UserRegistrationDTO userRegistrationDTO);

  long countUsers();

  void initAdminUser(String password);
}
