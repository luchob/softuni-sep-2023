package org.softuni.mobilele.init;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

  private final String defaultAdminPass;

  public DBInit(@Value("${default.admin.pass}") String defaultAdminPass) {
    this.defaultAdminPass = defaultAdminPass;
  }

  @Override
  public void run(String... args) throws Exception {
    //TODO
  }
}
