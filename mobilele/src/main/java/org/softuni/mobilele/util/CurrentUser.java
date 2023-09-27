package org.softuni.mobilele.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component("currentUser")
@SessionScope
public class CurrentUser {

  private String firstName;

  private String lastName;

  private boolean isLogged;

  public String getFirstName() {
    return firstName;
  }

  public CurrentUser setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFullName() {
    StringBuilder sb = new StringBuilder();
    if (firstName != null) {
      sb.append(firstName);
    }
    if (lastName != null) {
      if (!sb.isEmpty()) {
        sb.append(" ");
      }
      sb.append(lastName);
    }
    return sb.toString();
  }

  public CurrentUser setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public boolean isLogged() {
    return isLogged;
  }

  public CurrentUser setLogged(boolean logged) {
    isLogged = logged;
    return this;
  }

  public void logout() {
    setLogged(false);
    setFirstName(null);
    setLastName(null);
  }

  @Override
  public String toString() {
    return "CurrentUser{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", isLogged=" + isLogged +
        '}';
  }
}
