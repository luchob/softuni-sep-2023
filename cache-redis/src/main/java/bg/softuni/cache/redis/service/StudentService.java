package bg.softuni.cache.redis.service;

import bg.softuni.cache.redis.model.StudentDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  @Cacheable("students")
  public List<StudentDTO> getAllStudents() {

    System.out.println("Calculating students...");

    List<StudentDTO> allStudents = new ArrayList<>();
    allStudents.add(new StudentDTO("Pesho", 40));
    allStudents.add(new StudentDTO("Anna", 39));

    return allStudents;
  }


}
