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

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    private String lessonDescription;
//    private String studentId;
//    private String registrationId;
//    private String courseId;
//    private String instructorId;
    private LocalDate lessonDate;
    private String lessonTime;
    private String status;
}
