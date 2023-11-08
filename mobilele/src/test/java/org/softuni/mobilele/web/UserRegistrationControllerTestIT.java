package org.softuni.mobilele.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
class UserRegistrationControllerTestIT {

  @Autowired
  private MockMvc mockMvc;

  @Value("${mail.port}")
  private int port;

  @Value("${mail.host}")
  private String host;

  @Value("${mail.username}")
  private String username;

  @Value("${mail.password}")
  private String password;

  private GreenMail greenMail;

  @BeforeEach
  void setUp() {
    greenMail = new GreenMail(new ServerSetup(port, host,"smtp"));
    greenMail.start();
    greenMail.setUser(username, password);
  }

  @AfterEach
  void tearDown() {
    greenMail.stop();
  }

  @Test
  void testRegistration() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.post("/users/register")
            .param("email", "pesho@softuni.bg")
            .param("firstName", "Pesho")
            .param("lastName", "Petrov")
            .param("password", "topsecret")
            .param("confirmPassword", "topsecret")
            .with(csrf())
    ).andExpect(status().is3xxRedirection())
     .andExpect(view().name("redirect:/"));

    greenMail.waitForIncomingEmail(1);
    MimeMessage[] receivedMessages = greenMail.getReceivedMessages();

    Assertions.assertEquals(1, receivedMessages.length);
    MimeMessage registrationMessage = receivedMessages[0];

    Assertions.assertTrue(registrationMessage.getContent().toString().contains("Pesho"));
    Assertions.assertEquals(1, registrationMessage.getAllRecipients().length);
    Assertions.assertEquals("pesho@softuni.bg", registrationMessage.getAllRecipients()[0].toString());
  }
}