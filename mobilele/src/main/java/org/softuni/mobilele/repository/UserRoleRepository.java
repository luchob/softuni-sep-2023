package org.softuni.mobilele.repository;

import java.util.List;
import org.softuni.mobilele.model.entity.UserRoleEntity;
import org.softuni.mobilele.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

  List<UserRoleEntity> findAllByRoleIn(List<UserRoleEnum> roles);

}
