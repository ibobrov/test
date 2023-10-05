package i.bobrov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentView {

    private int id;

    private String fullName;

    private Date birthday;

    private int evaluationId;
}
