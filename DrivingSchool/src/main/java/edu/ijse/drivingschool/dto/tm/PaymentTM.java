package edu.ijse.drivingschool.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTM {
    private String paymentId;
    private String registrationId;
    private String paymentType;
    private String paymentMethod;
    private String paymentAmount;
    private LocalDate paymentDate;
    private String status;
}
