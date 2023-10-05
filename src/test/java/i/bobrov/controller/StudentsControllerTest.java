package i.bobrov.controller;

import i.bobrov.Main;
import i.bobrov.model.Evaluation;
import i.bobrov.model.Student;
import i.bobrov.service.SimpleStudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class StudentsControllerTest {

    @MockBean
    private SimpleStudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenGetStudentsThenReturnOkAndJson() throws Exception {
        when(studentService.findAll()).thenReturn(List.of(
                new Student(1, "name1", null, new Evaluation(1, "ok")),
                new Student()));
        this.mockMvc.perform(get("/students/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{\"id\":1,\"fullName\":\"name1\",\"birthday\":null,"
                                + "\"evaluation\":{\"id\":1,\"name\":\"ok\"}},"
                                + "{\"id\":0,\"fullName\":null,\"birthday\":null,\"evaluation\":null}]")
                );
    }
}