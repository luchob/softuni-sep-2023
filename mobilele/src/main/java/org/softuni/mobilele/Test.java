package org.softuni.mobilele;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.ProxyFactory;

public class Test {

  public static void main(String[] args) {
    ProxyFactory factory = new ProxyFactory(new SimplePojo());

    factory.addAdvice(
        (MethodBeforeAdvice) (method, args1, target) ->
            System.out.println("Before method " + target));

    Pojo pojo = (Pojo) factory.getProxy();
    // this is a method call on the proxy!
    pojo.foo();
  }

}

class SimplePojo implements Pojo {

  public void foo() {
    System.out.println("within");
  }

  public void bar() {
    // some logic...
  }
}

interface Pojo {
  void foo();
  void bar();
}
