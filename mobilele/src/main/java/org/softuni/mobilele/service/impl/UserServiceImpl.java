package org.softuni.mobilele.service.impl;

import org.softuni.mobilele.model.dto.UserRegistrationDTO;
import org.softuni.mobilele.model.entity.UserEntity;
import org.softuni.mobilele.model.events.UserRegisteredEvent;
import org.softuni.mobilele.repository.UserRepository;
import org.softuni.mobilele.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  private final ApplicationEventPublisher appEventPublisher;
  private final MobileleUserDetailsService mobileleUserDetailsService;

  public UserServiceImpl(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      ApplicationEventPublisher appEventPublisher) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.appEventPublisher = appEventPublisher;
    this.mobileleUserDetailsService = new MobileleUserDetailsService(userRepository);
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
  public void createUserIfNotExist(String email, String names) {

  }

  @Override
  public Authentication login(String email) {
    UserDetails userDetails = mobileleUserDetailsService.loadUserByUsername(email);

    Authentication auth = new UsernamePasswordAuthenticationToken(
        userDetails,
        userDetails.getPassword(),
        userDetails.getAuthorities()
    );

    SecurityContextHolder.getContext().setAuthentication(auth);

    return auth;
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
