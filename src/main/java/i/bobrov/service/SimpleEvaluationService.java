package i.bobrov.service;

import i.bobrov.dto.EvaluationDto;
import i.bobrov.model.Evaluation;
import i.bobrov.repository.EvaluationsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SimpleEvaluationService implements EvaluationService {
    private final EvaluationsRepository evaluationsRepo;

    @Override
    public List<Evaluation> findAll() {
        return evaluationsRepo.findAll();
    }

    @Override
    public boolean update(int id, EvaluationDto dto) {
        var rsl = false;
        var proxy = evaluationsRepo.findById(id);
        if (proxy.isPresent()) {
            proxy.get().setName(dto.getName());
            rsl = true;
        }
        return rsl;
    }
}
