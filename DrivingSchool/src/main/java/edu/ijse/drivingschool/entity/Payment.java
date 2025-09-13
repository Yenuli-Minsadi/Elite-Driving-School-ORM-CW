package edu.ijse.drivingschool.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    private String paymentId;
    private String registrationId;
    private String paymentType;
    private String paymentMethod;
    private String paymentAmount;
    private LocalDate paymentDate;
}
