package i.bobrov.controller;

import i.bobrov.model.Evaluation;
import i.bobrov.service.EvaluationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/evaluations")
@AllArgsConstructor
public class EvaluationsController {
    private final EvaluationService evaluationService;

    @GetMapping("/")
    public List<Evaluation> findAll() {
        return evaluationService.findAll();
    }
}
