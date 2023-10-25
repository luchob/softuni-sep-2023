package bg.softuni.plainwebapp;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(
    urlPatterns = "/hello-servlet"
)
public class HelloFilter implements Filter {
  // ---> Filter1 --> Filter2 ...  --> Servlet -> Browser
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    request.setAttribute("username", "Pesho");
    chain.doFilter(request, response);
  }
}
