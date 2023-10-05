package i.bobrov.service;

import i.bobrov.Main;
import i.bobrov.dto.EvaluationDto;
import i.bobrov.model.Evaluation;
import i.bobrov.repository.EvaluationsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class SimpleEvaluationServiceTest {

    @Autowired
    private SimpleEvaluationService evaluationService;

    @Autowired
    private EvaluationsRepository evaluationsRepo;

    @BeforeEach
    public void setUp() {
        evaluationsRepo.deleteAll();
    }

    @Test
    void whenFindAllThenReturnAll() {
        assertThat(evaluationService.findAll()).isEmpty();
        var ev1 = evaluationsRepo.save(new Evaluation(0, "test1"));
        var ev2 = evaluationsRepo.save(new Evaluation(0, "test2"));
        assertThat(evaluationService.findAll()).isEqualTo(List.of(ev1, ev2));
    }

    @Test
    void whenUpdateThenEffectedOnDataBase() {
        var ev1 = evaluationsRepo.save(new Evaluation(1, "test1"));
        assertThat(evaluationService.update(ev1.getId(), new EvaluationDto("new test"))).isTrue();
        assertThat(evaluationsRepo.findById(ev1.getId()).get())
                .usingRecursiveComparison()
                .isEqualTo(new Evaluation(ev1.getId(), "new test"));
    }

    @Test
    void whenNotUpdateThenNotEffectedOnDataBase() {
        var ev1 = evaluationsRepo.save(new Evaluation(1, "test1"));
        assertThat(evaluationService.update(-1, new EvaluationDto("new test"))).isFalse();
        assertThat(evaluationsRepo.findById(ev1.getId()).get()).isEqualTo(ev1);
    }
}