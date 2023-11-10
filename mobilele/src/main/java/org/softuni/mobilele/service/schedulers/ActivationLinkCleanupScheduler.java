package org.softuni.mobilele.service.schedulers;

import java.time.LocalDateTime;
import org.softuni.mobilele.service.UserActivationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ActivationLinkCleanupScheduler {

  private final UserActivationService userActivationService;

  public ActivationLinkCleanupScheduler(UserActivationService userActivationService) {
    this.userActivationService = userActivationService;
  }

  //@Scheduled(cron = "*/10 * * * * *")
  @Scheduled(fixedRate = 10_000)
  public void cleanUp() {
    userActivationService.cleanUpObsoleteActivationLinks();
  }
}
