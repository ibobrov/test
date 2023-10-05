package i.bobrov.common;

import i.bobrov.dto.StudentDto;
import i.bobrov.dto.StudentView;
import i.bobrov.model.Evaluation;
import i.bobrov.model.Student;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class ToolsTest {

    @Test
    void whenDtoConvertToEntityStudent() {
        var birthday = new Date(0);
        var dto = new StudentDto("test", birthday, 0);
        assertThat(Tools.convert(dto))
                .usingRecursiveComparison()
                .isEqualTo(
                        new Student(0, "test", birthday, null)
                );
    }

    @Test
    void whenDtoWithEvaluationConvertToEntityStudent() {
        var birthday = new Date(0);
        var dto = new StudentDto("test", birthday, 1);
        assertThat(Tools.convert(dto))
                .usingRecursiveComparison()
                .isEqualTo(
                        new Student(0, "test", birthday, new Evaluation(1, null))
                );
    }

    @Test
    void whenDtoConvertToViewDto() {
        var birthday = new Date(0);
        var dto = new StudentDto("test", birthday, 1);
        assertThat(Tools.convert(dto, 1))
                .usingRecursiveComparison()
                .isEqualTo(
                        new StudentView(1, "test", birthday, 1)
                );
    }
}