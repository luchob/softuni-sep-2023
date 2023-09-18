package org.softuni.service;

import java.util.Optional;
import org.softuni.model.User;

public interface UserService {
  Optional<User> findYoungestUser();
}
