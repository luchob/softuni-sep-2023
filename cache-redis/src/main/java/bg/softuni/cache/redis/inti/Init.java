package bg.softuni.cache.redis.inti;

import bg.softuni.cache.redis.service.StudentService;
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
    studentService.getAllStudents();
    studentService.getAllStudents();
    studentService.getAllStudents();
    studentService.getAllStudents().forEach(
        System.out::println
    );
  }
}
