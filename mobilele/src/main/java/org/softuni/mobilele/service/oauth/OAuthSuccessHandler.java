package org.softuni.mobilele.service.oauth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.softuni.mobilele.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class OAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  private final UserService userService;

  public OAuthSuccessHandler(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws ServletException, IOException {

    if (authentication instanceof OAuth2AuthenticationToken token) {

      OAuth2User user = token.getPrincipal();

      String email = user
          .getAttribute("email");
      String name = user
          .getAttribute("name");

      userService.createUserIfNotExist(email, name);
      authentication = userService.login(email);
    }

    super.onAuthenticationSuccess(request, response, authentication);
  }
}
