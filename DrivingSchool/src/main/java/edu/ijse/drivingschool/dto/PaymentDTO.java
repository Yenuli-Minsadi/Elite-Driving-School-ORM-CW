package edu.ijse.drivingschool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private String paymentId;
    private String registrationId;
    private String paymentType;
    private String paymentMethod;
    private String paymentAmount;
    private LocalDate paymentDate;
    private String status;
}
