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
@Table(name = "registration")
public class Registration {
    @Id
    private String registrationId;
    private String studentId;
    private String consultationId;
    private String paymentId;
    private LocalDate registrationDate;
    private String status;
}
