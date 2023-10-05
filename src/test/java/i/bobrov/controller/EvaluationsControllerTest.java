package i.bobrov.controller;

import i.bobrov.Main;
import i.bobrov.model.Evaluation;
import i.bobrov.service.SimpleEvaluationService;
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
class EvaluationsControllerTest {

    @MockBean
    private SimpleEvaluationService evaluationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenGetEvaluationsThenReturnOkAndJson() throws Exception {
        when(evaluationService.findAll()).thenReturn(List.of(
                new Evaluation(1, "ok"), new Evaluation(2, "cool"))
        );
        this.mockMvc.perform(get("/evaluations/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{\"id\":1,\"name\":\"ok\"},{\"id\":2,\"name\":\"cool\"}]")
                );
    }
}