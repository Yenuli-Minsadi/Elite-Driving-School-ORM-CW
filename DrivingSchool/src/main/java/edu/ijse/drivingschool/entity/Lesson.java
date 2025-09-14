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
@Table(name = "lesson")
public class Lesson {
    @Id
    private String lessonId;
    private String lessonName;
    private String lessonDescription;
//    private String registrationId;
    private String courseId;
    private String instructorId;
//    private LocalDate lessonDate;
//    private String lessonTime;
    private String status;
}
