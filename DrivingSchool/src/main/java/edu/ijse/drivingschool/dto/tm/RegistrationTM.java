package edu.ijse.drivingschool.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationTM {
    private String registrationId;
    private String studentId;
    private String courseId;
    private String processedBy; //user id fk
    private String paymentId;
    private LocalDate registrationDate;
    private String status;
}
