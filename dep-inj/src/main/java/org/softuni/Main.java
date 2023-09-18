package org.softuni;

import org.softuni.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext("org.softuni");

    UserService userService1 = context.getBean(UserService.class);
    UserService userService2 = context.getBean(UserService.class);

    System.out.println(userService1 == userService2);
  }
}