package i.bobrov.repository;

import i.bobrov.model.Evaluation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EvaluationsRepository extends CrudRepository<Evaluation, Integer> {

    @Override
    List<Evaluation> findAll();
}
