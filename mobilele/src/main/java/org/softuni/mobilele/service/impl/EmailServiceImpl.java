package org.softuni.mobilele.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.softuni.mobilele.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {

  private final TemplateEngine templateEngine;
  private final JavaMailSender javaMailSender;
  private final String mobileleEmail;

  public EmailServiceImpl(
      TemplateEngine templateEngine,
      JavaMailSender javaMailSender,
      @Value("${mail.mobilele}") String mobileleEmail) {
    this.templateEngine = templateEngine;
    this.javaMailSender = javaMailSender;
    this.mobileleEmail = mobileleEmail;
  }

  @Override
  public void sendRegistrationEmail(String userEmail, String userName)  {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();

    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

    try {

      mimeMessageHelper.setTo(userEmail);
      mimeMessageHelper.setFrom(mobileleEmail);
      mimeMessageHelper.setReplyTo(mobileleEmail);
      mimeMessageHelper.setSubject("Welcome to mobilele!");
      mimeMessageHelper.setText(generateRegistrationEmailBody(userName), true);

      javaMailSender.send(mimeMessageHelper.getMimeMessage());

    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }

  private String generateRegistrationEmailBody(String userName) {

    Context context = new Context();
    context.setVariable("username", userName);

    return templateEngine.process("email/registration-email", context);
  }
}
