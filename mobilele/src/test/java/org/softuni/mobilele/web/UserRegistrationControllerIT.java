package org.softuni.mobilele.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerIT {

  @Autowired
  private MockMvc mockMvc;

  private GreenMail greenMail;

  @Value("${mail.host}")
  private String host;

  @Value("${mail.port}")
  private Integer port;

  @Value("${mail.username}")
  private String username;

  @Value("${mail.password}")
  private String password;

  @BeforeEach
  void setup() {
    greenMail = new GreenMail(new ServerSetup(port, host, "smtp"));
    greenMail.start();
    greenMail.setUser(username, username, password);
  }

  @AfterEach
  void tearDown() {
    greenMail.stop();
  }

  @Test
  void testRegistration() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/users/register")
            .param("firstName", "Pesho")
            .param("lastName", "Petrov")
            .param("email", "pesho@example.com")
            .param("password", "topsecret")
            .param("confirmPassword", "topsecret")
            .with(csrf())).
      andExpect(status().is3xxRedirection());

    greenMail.waitForIncomingEmail(2000, 1);
    MimeMessage[] messages = greenMail.getReceivedMessages();

    Assertions.assertEquals(1, messages.length);

    System.out.println(messages[0].getContent());
  }

}
