package bg.softuni.proxies;

import java.lang.reflect.Proxy;

public class Main {

  public static void main(String[] args) {

    StudentServiceIfc studentServiceIfc = createStudentService();
    studentServiceIfc.getAllStudents();
    studentServiceIfc.getAllStudents();
    studentServiceIfc.getAllStudents();
  }

  public static StudentServiceIfc createStudentService() {
    return (StudentServiceIfc)Proxy.newProxyInstance(
        Main.class.getClassLoader(),
        new Class[]{StudentServiceIfc.class},
        new CacheableInvocationHandler(new StudentService())
    );
  }
}