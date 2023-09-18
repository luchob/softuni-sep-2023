package org.softuni.bootdemo.util;

import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class CLGreeter implements CommandLineRunner {

  private final Gson gson;

  public CLGreeter(Gson gson) {
    this.gson = gson;
  }

  @Override
  public void run(String... args) throws Exception {
    var json = gson.toJson(new Point(1, 4));
    System.out.println("My point: " + json);
  }

  record Point(int x, int y) {

  }
}
