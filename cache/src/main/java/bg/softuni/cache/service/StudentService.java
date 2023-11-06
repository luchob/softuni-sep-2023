package bg.softuni.cache.service;

import bg.softuni.cache.model.StudentDTO;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  public List<StudentDTO> getStudentsNotAnnotated() {
    return getStudents();
  }

  @Cacheable("students")
  public List<StudentDTO> getStudents() {
    System.out.println("getStudents() called");

    List<StudentDTO> allStudents = new ArrayList<>();

    allStudents.add(new StudentDTO("Ivan", 20));
    allStudents.add(new StudentDTO("Maria", 19));

    return allStudents;
  }

}
