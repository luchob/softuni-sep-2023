package org.softuni.mobilele.service.impl;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softuni.mobilele.model.entity.UserActivationCodeEntity;
import org.softuni.mobilele.model.events.UserRegisteredEvent;
import org.softuni.mobilele.repository.UserActivationCodeRepository;
import org.softuni.mobilele.repository.UserRepository;
import org.softuni.mobilele.service.EmailService;
import org.softuni.mobilele.service.UserActivationService;
import org.softuni.mobilele.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserActivationServiceImpl implements UserActivationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserActivationServiceImpl.class);
  private static final String ACTIVATION_CODE_SYMBOLS = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789";
  private static final int ACTIVATION_CODE_LENGTH = 20;
  private final EmailService emailService;
  private final UserRepository userRepository;
  private final UserActivationCodeRepository userActivationCodeRepository;
  private final boolean mailEnabled;

  public UserActivationServiceImpl(EmailService emailService,
      UserRepository userRepository,
      UserActivationCodeRepository userActivationCodeRepository,
      @Value("${mail.enabled}") boolean mailEnabled) {
    this.emailService = emailService;
    this.userRepository = userRepository;
    this.userActivationCodeRepository = userActivationCodeRepository;
    this.mailEnabled = mailEnabled;
  }

  @Override
  @EventListener(UserRegisteredEvent.class)
  public void userRegistered(UserRegisteredEvent event) {
    if (mailEnabled) {
      String activationCode = createActivationCode(event.getUserEmail());
      emailService.sendRegistrationEmail(event.getUserEmail(),
          event.getUserNames(),
          activationCode);
    } else {
      LOGGER.info("User registered but no email service is available");
    }
  }

  @Override
  public void cleanUpObsoleteActivationLinks() {
    //TODO: Implement
//    System.out.println("NOT YET");
  }

  public String createActivationCode(String userEmail) {

    String activationCode = generateActivationCode();

    UserActivationCodeEntity userActivationCodeEntity = new UserActivationCodeEntity();
    userActivationCodeEntity.setActivationCode(activationCode);
    userActivationCodeEntity.setCreated(Instant.now());
    userActivationCodeEntity.setUser(userRepository.findByEmail(userEmail).orElseThrow(() -> new ObjectNotFoundException("User not found")));

    userActivationCodeRepository.save(userActivationCodeEntity);

    return activationCode;

  }

  private static String generateActivationCode() {

    StringBuilder activationCode = new StringBuilder();
    Random random = new SecureRandom();

    for (int i = 0; i < ACTIVATION_CODE_LENGTH; i++) {
      int randInx = random.nextInt(ACTIVATION_CODE_SYMBOLS.length());
      activationCode.append(ACTIVATION_CODE_SYMBOLS.charAt(randInx));
    }

    return activationCode.toString();
  }


}
