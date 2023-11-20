package org.softuni.mobilele.testutils;

import java.util.List;
import org.softuni.mobilele.model.entity.UserEntity;
import org.softuni.mobilele.model.enums.UserRoleEnum;
import org.softuni.mobilele.repository.UserRepository;
import org.softuni.mobilele.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTestDataUtil {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserRoleRepository userRoleRepository;

  public UserEntity createTestUser(String email) {
    return createUser(email, List.of(UserRoleEnum.USER));
  }

  public UserEntity createTestAdmin(String email) {
    return createUser(email, List.of(UserRoleEnum.ADMIN));
  }

  private UserEntity createUser(String email, List<UserRoleEnum> roles) {

    var roleEntities = userRoleRepository.findAllByRoleIn(roles);

    UserEntity newUser = new UserEntity()
        .setActive(true)
        .setEmail(email)
        .setFirstName("Test user first")
        .setLastName("Test user last")
        .setRoles(
            roleEntities
        );

    return userRepository.save(newUser);
  }

  public void cleanUp() {
    userRepository.deleteAll();
  }

}
