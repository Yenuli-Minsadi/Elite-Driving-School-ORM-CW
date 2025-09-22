package edu.ijse.drivingschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    private String paymentId;

    @ManyToOne
    @JoinColumn(name="registration_id", nullable = false)
    private Registration registration;

    private String paymentType;
    private String paymentMethod;
    private String paymentAmount;
    private LocalDate paymentDate;
    private String status;//completed, pending
}
