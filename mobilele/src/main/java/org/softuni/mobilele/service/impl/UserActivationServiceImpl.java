package org.softuni.mobilele.service.impl;

import org.softuni.mobilele.model.events.UserRegisteredEvent;
import org.softuni.mobilele.service.EmailService;
import org.softuni.mobilele.service.UserActivationService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserActivationServiceImpl implements UserActivationService {

  private final EmailService emailService;

  public UserActivationServiceImpl(EmailService emailService) {
    this.emailService = emailService;
  }

  @Override
  @EventListener(UserRegisteredEvent.class)
  public void userRegistered(UserRegisteredEvent event) {
    // TODO: ADD Activation links
    emailService.sendRegistrationEmail(event.getUserEmail(), event.getUserNames());
  }

  @Override
  public void cleanUpObsoleteActivationLinks() {
    //TODO: Implement
    System.out.println("NOT YET");
  }


}
