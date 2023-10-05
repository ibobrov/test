package i.bobrov.service;

import i.bobrov.common.Tools;
import i.bobrov.dto.StudentDto;
import i.bobrov.model.Student;
import i.bobrov.repository.StudentsRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleStudentService implements StudentService {
    private final StudentsRepository studentsRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleStudentService.class);

    @Override
    public List<Student> findAll() {
        return studentsRepo.findAll();
    }

    @Override
    public Optional<Student> save(StudentDto dto) {
        try {
            var student = Tools.convert(dto);
            return Optional.of(studentsRepo.save(student));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public boolean update(int id, StudentDto dto) {
        var rsl = false;
        var proxy = studentsRepo.findById(id);
        if (proxy.isPresent()) {
            var student = Tools.convert(dto);
            proxy.get().setFullName(student.getFullName());
            proxy.get().setBirthday(student.getBirthday());
            proxy.get().setEvaluation(student.getEvaluation());
            rsl = true;
        }
        return rsl;
    }

    @Override
    public void delete(int id) {
        studentsRepo.deleteById(id);
    }
}
