package org.softuni.repository;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import org.softuni.model.User;
import org.springframework.stereotype.Repository;

@Repository("fileRepo")
public class FileBasedUserRepository implements UserRepository {

  @Override
  public List<User> findAll() {
    return new BufferedReader(
        new InputStreamReader(ClassLoader.getSystemResourceAsStream("users.csv")))
        .lines()
        .map(this::parseUser)
        .toList();
  }

  private User parseUser(String line) {
    String[] tokens = line.split(",");
    return new User(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
  }
}
