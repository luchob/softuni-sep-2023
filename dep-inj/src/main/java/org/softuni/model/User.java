package org.softuni.model;

public record User(
    String firstName,
    String lastName,
    int age) {

  public String description() {
    return firstName() + " " + lastName() + ", Age: " + age;
  }
}
