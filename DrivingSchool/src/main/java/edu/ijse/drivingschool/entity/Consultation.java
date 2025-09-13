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
@Table(name = "consultation")
public class Consultation {
    @Id
    private String consultationId;
    private String studentId;
    private String coordinatorId;
    private LocalDate consultationDate;
    private String status;
}
