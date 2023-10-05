package i.bobrov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    @Size(min = 3, max = 500)
    @NotBlank(message = "Name is mandatory")
    private String fullName;

    @NotNull(message = "Birthday is mandatory")
    private Date birthday;

    private int evaluationId;
}
