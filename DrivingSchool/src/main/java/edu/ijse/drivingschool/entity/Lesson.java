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
    private String lessonDescription;

    // Each lesson belongs to a registration (student + course)
    @ManyToOne
    @JoinColumn(name="registration_id", nullable = false)
    private Registration registration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name="course_id", nullable = false)
    private Course course;

    private LocalDate lessonDate;
    private String lessonTime;
    private String status;
}
