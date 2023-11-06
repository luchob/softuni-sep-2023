package org.softuni.mobilele.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_activation_codes")
public class UserActivationCodeEntity extends BaseEntity {

  private String activationCode;

  private Instant created;

  @ManyToOne
  private UserEntity user;

  public String getActivationCode() {
    return activationCode;
  }

  public UserActivationCodeEntity setActivationCode(String activationCode) {
    this.activationCode = activationCode;
    return this;
  }

  public Instant getCreated() {
    return created;
  }

  public UserActivationCodeEntity setCreated(Instant created) {
    this.created = created;
    return this;
  }

  public UserEntity getUser() {
    return user;
  }

  public UserActivationCodeEntity setUser(UserEntity user) {
    this.user = user;
    return this;
  }
}
