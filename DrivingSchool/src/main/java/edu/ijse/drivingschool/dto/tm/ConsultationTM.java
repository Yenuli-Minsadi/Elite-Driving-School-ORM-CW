package edu.ijse.drivingschool.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationTM {
    private String consultationId;
    private String studentId;
    private String coordinatorId;
    private LocalDate consultationDate;
    private String status;
}
