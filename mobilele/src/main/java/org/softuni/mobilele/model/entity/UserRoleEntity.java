package org.softuni.mobilele.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.softuni.mobilele.model.enums.UserRoleEnum;

@Table(name = "roles")
@Entity
public class UserRoleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private UserRoleEnum role;

  public Long getId() {
    return id;
  }

  public UserRoleEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public UserRoleEnum getRole() {
    return role;
  }

  public UserRoleEntity setRole(UserRoleEnum role) {
    this.role = role;
    return this;
  }
}
