package org.softuni.mobilele.repository;

import org.softuni.mobilele.model.entity.UserEntity;
import org.softuni.mobilele.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository  extends JpaRepository<UserRoleEntity, Long> {

}
