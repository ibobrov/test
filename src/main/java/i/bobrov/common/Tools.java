package i.bobrov.common;

import i.bobrov.dto.StudentDto;
import i.bobrov.dto.StudentView;
import i.bobrov.model.Evaluation;
import i.bobrov.model.Student;

/**
 * Можно было бы заменить на MapStruct, но библиотеку для пары методов подключать не хочется.
 */
public interface Tools {

    static Student convert(StudentDto dto) {
        var student = new Student();
        student.setFullName(dto.getFullName());
        student.setBirthday(dto.getBirthday());
        var evaluationId = dto.getEvaluationId();
        if (evaluationId != 0) {
            var assessment = new Evaluation();
            assessment.setId(evaluationId);
            student.setEvaluation(assessment);
        }
        return student;
    }

    static StudentView convert(StudentDto dto, int id) {
        return new StudentView(id, dto.getFullName(), dto.getBirthday(), dto.getEvaluationId());
    }
}
