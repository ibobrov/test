package i.bobrov.controller;

import i.bobrov.dto.EvaluationDto;
import i.bobrov.service.EvaluationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/evaluation")
@AllArgsConstructor
public class EvaluationController {
    private final EvaluationService evaluationService;

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @Valid @RequestBody EvaluationDto dto) {
        if (!evaluationService.update(id, dto)) {
            throw new ResponseStatusException(
                    HttpStatus.OK, dto + " dont exists."
            );
        }
        return ResponseEntity.ok().build();
    }
}
