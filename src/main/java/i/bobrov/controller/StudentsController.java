package i.bobrov.controller;

import i.bobrov.model.Student;
import i.bobrov.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentsController {
    private final StudentService studentService;

    @GetMapping("/")
    public List<Student> findAll() {
        return studentService.findAll();
    }
}
