package i.bobrov.controller;

import i.bobrov.Main;
import i.bobrov.dto.EvaluationDto;
import i.bobrov.service.SimpleEvaluationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class EvaluationControllerTest {

    @MockBean
    private SimpleEvaluationService evaluationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenPutValidEvaluationThenReturnOk() throws Exception {
        when(evaluationService.update(1, new EvaluationDto("ok"))).thenReturn(true);
        this.mockMvc.perform(put("/evaluation/1")
                        .contentType("application/json")
                        .content("{\"name\":\"ok\"}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void whenPutInvalidEvaluationThenReturnBadRequest() throws Exception {
        this.mockMvc.perform(put("/evaluation/1")
                        .contentType("application/json")
                        .content("{\"name\":\"\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("[{\"name\":\"Name is mandatory. Actual value: \"}]"));
    }

    @Test
    void whenPutAndNotFoundIdThenReturnBadRequest() throws Exception {
        var error = Objects.requireNonNull(this.mockMvc.perform(put("/evaluation/1")
                        .contentType("application/json")
                        .content("{\"name\":\"ok\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResolvedException()).getMessage();
        assertThat(error).isEqualTo("200 OK \"EvaluationDto(name=ok) dont exists.\"");
    }
}