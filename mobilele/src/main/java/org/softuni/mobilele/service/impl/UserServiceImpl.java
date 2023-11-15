package org.softuni.mobilele.service.impl;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import org.softuni.mobilele.model.dto.UserRegistrationDTO;
import org.softuni.mobilele.model.entity.UserEntity;
import org.softuni.mobilele.model.entity.UserRoleEntity;
import org.softuni.mobilele.model.events.UserRegisteredEvent;
import org.softuni.mobilele.repository.UserRepository;
import org.softuni.mobilele.repository.UserRoleRepository;
import org.softuni.mobilele.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;
  private final PasswordEncoder passwordEncoder;

  private final ApplicationEventPublisher appEventPublisher;

  public UserServiceImpl(
      UserRepository userRepository,
      UserRoleRepository userRoleRepository,
      PasswordEncoder passwordEncoder,
      ApplicationEventPublisher appEventPublisher) {
    this.userRepository = userRepository;
    this.userRoleRepository = userRoleRepository;
    this.passwordEncoder = passwordEncoder;
    this.appEventPublisher = appEventPublisher;
  }

  @Override
  public void registerUser(
      UserRegistrationDTO userRegistrationDTO) {

    userRepository.save(map(userRegistrationDTO));

    appEventPublisher.publishEvent(new UserRegisteredEvent(
        "UserService",
        userRegistrationDTO.email(),
        userRegistrationDTO.fullName()
    ));
  }

  @Override
  public long countUsers() {
    return userRepository.count();
  }

  @Override
  public void initAdminUser(String password) {
    UserEntity admin = new UserEntity();
    admin.setFirstName("Admin");
    admin.setLastName("Adminov");
    admin.setEmail("admin@example.com");
    admin.setPassword(passwordEncoder.encode(password));
    admin.setActive(true);

    admin.setRoles(userRoleRepository.findAll());

    userRepository.save(admin);
  }

  private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
    return new UserEntity()
        .setActive(false)
        .setFirstName(userRegistrationDTO.firstName())
        .setLastName(userRegistrationDTO.lastName())
        .setEmail(userRegistrationDTO.email())
        .setPassword(passwordEncoder.encode(userRegistrationDTO.password()));
  }
}
