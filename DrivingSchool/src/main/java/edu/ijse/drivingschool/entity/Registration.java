package edu.ijse.drivingschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "registration ")
public class Registration {
    @Id
    private String registrationId;
//    private String studentId;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

//    private String courseId;
    private String processedBy; //user id fk
    private String paymentId;
    private LocalDate registrationDate;
    private String status;
}
