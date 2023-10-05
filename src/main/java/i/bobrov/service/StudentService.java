package i.bobrov.service;

import i.bobrov.dto.StudentDto;
import i.bobrov.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> findAll();

    Optional<Student> save(StudentDto dto);

    boolean update(int id, StudentDto dto);

    void delete(int id);
}
