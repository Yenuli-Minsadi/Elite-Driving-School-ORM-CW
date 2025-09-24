package edu.ijse.drivingschool.dto;

import edu.ijse.drivingschool.entity.Registration;
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

    public PaymentDTO(String paymentId, Registration registration, String paymentType, String paymentMethod, String paymentAmount, LocalDate paymentDate, String status) {

            this.paymentId=paymentId;
            this.registrationId=registration.getRegistrationId();
            this.paymentType=paymentType;
            this.paymentMethod=paymentMethod;
            this.paymentAmount=paymentAmount;
            this.paymentDate=paymentDate;
            this.status=status;

    }
}
