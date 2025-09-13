package edu.ijse.drivingschool.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private String address;
    private LocalDate dob;
}
