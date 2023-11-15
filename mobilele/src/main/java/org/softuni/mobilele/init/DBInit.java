package org.softuni.mobilele.init;

import org.softuni.mobilele.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

  private final String defaultAdminPass;
  private final UserService userService;

  public DBInit(@Value("${mobilele.default.admin.pass}") String defaultAdminPass,
      UserService userService) {
    this.defaultAdminPass = defaultAdminPass;
    this.userService = userService;
  }

  @Override
  public void run(String... args) throws Exception {
    if (userService.countUsers() == 0) {
      userService.initAdminUser(defaultAdminPass);
    };
  }
}
