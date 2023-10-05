package i.bobrov.service;

import i.bobrov.dto.EvaluationDto;
import i.bobrov.model.Evaluation;

import java.util.List;

public interface EvaluationService {

    List<Evaluation> findAll();

    boolean update(int id, EvaluationDto dto);
}
