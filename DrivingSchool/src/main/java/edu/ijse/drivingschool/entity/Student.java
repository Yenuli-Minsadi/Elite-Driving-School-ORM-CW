package edu.ijse.drivingschool.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    private String studentId;
    private String firstName;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Lesson> lesson;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Payment> payment;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Registration> registration;

    private String lastName;
    private String email;
    private int phone;
    private String address;
    private LocalDate dob;
}
