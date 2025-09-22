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
@Table(name = "student")
public class Student {
    @Id
    private String studentId;
    private String firstName;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Registration> registration;

    private String lastName;
    private String email;
    private String phone;
    private String address;
    private LocalDate dob;

}
