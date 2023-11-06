package bg.softuni.proxies;

import java.util.List;

public class StudentService implements StudentServiceIfc {
  @Override
  @Cacheable("students")
  public List<StudentDTO> getAllStudents() {

    System.out.println("CALCULATING ALL STUDENTS");

    return List.of(
         new StudentDTO("Pesho", 21),
         new StudentDTO("Anna", 21)
    );
  }
}
