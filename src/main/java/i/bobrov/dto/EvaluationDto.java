package i.bobrov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDto {

    @NotBlank(message = "Name is mandatory")
    private String name;
}
