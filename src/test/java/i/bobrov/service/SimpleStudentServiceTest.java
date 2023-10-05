package i.bobrov.service;

import i.bobrov.Main;
import i.bobrov.dto.StudentDto;
import i.bobrov.model.Student;
import i.bobrov.repository.StudentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class SimpleStudentServiceTest {

    @Autowired
    private SimpleStudentService studentService;

    @Autowired
    private StudentsRepository studentsRepo;

    @BeforeEach
    public void setUp() {
        studentsRepo.deleteAll();
    }

    @Test
    void whenFindAllThenReturnAll() {
        assertThat(studentService.findAll()).isEmpty();
        var st1 = studentsRepo.save(new Student(1, "name1", new Date(0), null));
        var st2 = studentsRepo.save(new Student(2, "name2", new Date(0), null));
        assertThat(studentService.findAll()).isEqualTo(List.of(st1, st2));
    }

    @Test
    void whenSaveThenEffectedOnDataBase() {
        var savedOpt = studentService.save(new StudentDto("name", new Date(0), 0));
        assertThat(savedOpt.isPresent()).isTrue();
        assertThat(studentsRepo.findAll()).isEqualTo(List.of(savedOpt.get()));
    }

    @Test
    void whenTrySaveDuplicateThenServiceReturnEmpty() {
        assertThat(studentService.save(new StudentDto("name", new Date(0), 0))).isPresent();
        assertThat(studentService.save(new StudentDto("name", new Date(0), 0))).isEmpty();
    }

    @Test
    void whenUpdateThenEffectedOnDataBase() {
        var st1 = studentsRepo.save(new Student(1, "name1", new Date(0), null));
        assertThat(studentService.update(
                st1.getId(), new StudentDto("name2", new Date(1), 0))
        ).isTrue();
        assertThat(studentsRepo.findById(st1.getId()).get())
                .usingRecursiveComparison()
                .ignoringFields("birthday")
                .isEqualTo(new Student(st1.getId(), "name2", new Date(1), null));
    }

    @Test
    void whenNotUpdateThenNotEffectedOnDataBase() {
        var st1 = studentsRepo.save(new Student(1, "name1", new Date(0), null));
        assertThat(studentService.update(-1, new StudentDto("name", new Date(1), 0))).isFalse();
        assertThat(studentsRepo.findById(st1.getId()).get()).isEqualTo(st1);
    }

    @Test
    void whenDeleteThenEffectedOnDataBase() {
        var st1 = studentsRepo.save(new Student(1, "name1", new Date(0), null));
        studentService.delete(st1.getId());
        assertThat(studentsRepo.findAll()).isEmpty();
    }
}