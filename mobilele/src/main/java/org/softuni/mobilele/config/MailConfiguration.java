package org.softuni.mobilele.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

  @Bean
  public JavaMailSender javaMailSender(
      @Value("${mail.host}") String host,
      @Value("${mail.port}") int port,
      @Value("${mail.username}") String username,
      @Value("${mail.password}") String password) {
    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

    javaMailSender.setHost(host);
    javaMailSender.setPort(port);
    javaMailSender.setUsername(username);
    javaMailSender.setPassword(password);
    javaMailSender.setDefaultEncoding("UTF-8");
    javaMailSender.setJavaMailProperties(mailProperties());

    return javaMailSender;
  }

  private Properties mailProperties() {
    Properties properties = new Properties();

    properties.setProperty("mail.smtp.auth", "true");
    properties.setProperty("mail.transport.protocol", "smtp");

    return properties;
  }
}
