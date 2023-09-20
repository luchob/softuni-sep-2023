package org.softuni.mvcfn.web;

import java.util.Map;
import org.apache.catalina.Server;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
public class TestHandlers {
  public ServerResponse test(ServerRequest serverRequest) {
    return ServerResponse.ok().render("test",
        Map.of("message", "Hello, Pesho!"));
  }

}
