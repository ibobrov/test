package i.bobrov.controller;

import i.bobrov.Main;
import i.bobrov.dto.StudentDto;
import i.bobrov.model.Student;
import i.bobrov.service.SimpleStudentService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Objects;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class StudentControllerTest {

    @MockBean
    private SimpleStudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenPostStudentWithValidArgsThenReturnCreated() throws Exception {
        when(studentService.save(any()))
                .thenReturn(of(new Student(1, "name", new Date(0), null)));
        this.mockMvc.perform(post("/student/")
                        .contentType("application/json")
                        .content("{\"fullName\":\"test\", \"birthday\":\"1970-01-01\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1,\"fullName\":\"test\","
                        + "\"birthday\":\"1970-01-01T00:00:00.000+00:00\",\"evaluationId\":0}"));
        var argument = ArgumentCaptor.forClass(StudentDto.class);
        verify(studentService).save(argument.capture());
        assertThat(argument.getValue().getFullName()).isEqualTo("test");
        assertThat(argument.getValue().getBirthday()).isEqualTo(new Date(0));
        assertThat(argument.getValue().getEvaluationId()).isEqualTo(0);
    }

    @Test
    void whenPostStudentWithValidArgsThenReturnOkAndExistMsg() throws Exception {
        when(studentService.save(any())).thenReturn(empty());
        var error = Objects.requireNonNull(this.mockMvc.perform(post("/student/")
                        .contentType("application/json")
                        .content("{\"fullName\":\"test\", \"birthday\":\"1970-01-01\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResolvedException()).getMessage();
        assertThat(error.contains("already exists")).isTrue();
    }

    @Test
    void whenPostStudentWithInvalidNameThenReturnBadRequestAndError() throws Exception {
        var error = Objects.requireNonNull(this.mockMvc.perform(post("/student/")
                        .contentType("application/json")
                        .content("{\"fullName\":\"\", \"birthday\":\"1970-01-01\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException()).getMessage();
        assertThat(error.contains("Name is mandatory")).isTrue();
    }

    @Test
    void whenPostStudentWithBirthdayThenReturnBadRequestAndError() throws Exception {
        var error = Objects.requireNonNull(this.mockMvc.perform(post("/student/")
                        .contentType("application/json")
                        .content("{\"fullName\":\"test\", \"birthday\":\"\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException()).getMessage();
        assertThat(error.contains("Birthday is mandatory")).isTrue();
    }

    @Test
    void whenPutStudentThenReturnOk() throws Exception {
        when(studentService.update(1, new StudentDto("test", new Date(0), 0)))
                .thenReturn(true);
        this.mockMvc.perform(put("/student/1")
                .contentType("application/json")
                .content("{\"fullName\":\"test\", \"birthday\":\"1970-01-01\"}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void whenPutStudentThenReturnOkAndError() throws Exception {
        when(studentService.update(1, new StudentDto("test", new Date(0), 0)))
                .thenReturn(false);
        var error = Objects.requireNonNull(this.mockMvc.perform(put("/student/1")
                        .contentType("application/json")
                        .content("{\"fullName\":\"test\", \"birthday\":\"1970-01-01\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResolvedException()).getMessage();
        assertThat(error.contains("dont exists")).isTrue();
    }

    @Test
    void whenPutStudentWithInvalidNameThenReturnBadRequest() throws Exception {
        var error = Objects.requireNonNull(this.mockMvc.perform(put("/student/1")
                        .contentType("application/json")
                        .content("{\"fullName\":\"\", \"birthday\":\"1970-01-01\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException()).getMessage();
        assertThat(error.contains("Name is mandatory")).isTrue();
    }

    @Test
    void whenPutStudentWithInvalidBirthdayThenReturnBadRequest() throws Exception {
        var error = Objects.requireNonNull(this.mockMvc.perform(put("/student/1")
                        .contentType("application/json")
                        .content("{\"fullName\":\"test\", \"birthday\":\"\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException()).getMessage();
        assertThat(error.contains("Birthday is mandatory")).isTrue();
    }

    @Test
    void whenDeleteStudentThenReturnOk() throws Exception {
        this.mockMvc.perform(delete("/student/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}