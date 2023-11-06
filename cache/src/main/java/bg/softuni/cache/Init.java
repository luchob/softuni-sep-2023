package bg.softuni.cache;

import bg.softuni.cache.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {

  private final StudentService studentService;

  public Init(StudentService studentService) {
    this.studentService = studentService;
  }

  @Override
  public void run(String... args) throws Exception {
    studentService.getStudentsNotAnnotated();
    studentService.getStudentsNotAnnotated();
    studentService
        .getStudentsNotAnnotated()
        .forEach(s -> System.out.println(s.name() + " " + s.age()));
  }
}
