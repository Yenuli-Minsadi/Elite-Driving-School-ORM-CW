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
@Table(name = "lesson")
public class Lesson {
    @Id
    private String lessonId;
    private String lessonName;

    // Each lesson belongs to a registration (student + course)
    @ManyToOne
    @JoinColumn(name="registration_id", nullable = false)
    private Registration registration;

    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    private String lessonDescription;
    private LocalDate lessonDate;
    private String lessonTime;
    private String status;
}
