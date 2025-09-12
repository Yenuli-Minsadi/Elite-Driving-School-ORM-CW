package edu.ijse.drivingschool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationDTO {
    private String consultationId;
    private String studentId;
    private String coordinatorId;
    private LocalDate consultationDate;
    private String status;
}
