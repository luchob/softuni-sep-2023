package org.softuni.repository;

import java.util.List;
import org.softuni.model.User;

public interface UserRepository {
  List<User> findAll();

}
