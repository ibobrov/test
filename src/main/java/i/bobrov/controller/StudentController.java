package i.bobrov.controller;

import i.bobrov.common.Tools;
import i.bobrov.dto.StudentDto;
import i.bobrov.dto.StudentView;
import i.bobrov.service.SimpleStudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {
    private final SimpleStudentService studentService;

    @PostMapping("/")
    public ResponseEntity<StudentView> create(@Valid @RequestBody StudentDto dto) {
        var created = studentService.save(dto);
        if (created.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.OK, dto + " already exists."
            );
        }
        var createdView = Tools.convert(dto, created.get().getId());
        return new ResponseEntity<>(
                createdView,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @Valid @RequestBody StudentDto dto) {
        if (!studentService.update(id, dto)) {
            throw new ResponseStatusException(
                    HttpStatus.OK, dto + " dont exists."
            );
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }
}
