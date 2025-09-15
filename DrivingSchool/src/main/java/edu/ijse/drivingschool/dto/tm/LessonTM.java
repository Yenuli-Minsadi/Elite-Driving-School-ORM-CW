package edu.ijse.drivingschool.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonTM {
    private String lessonId;
    private String lessonName;
    private String lessonDescription;
    private String studentId;
    //    private String registrationId;
    private String courseId;
    private String instructorId;
    private LocalDate lessonDate;
    private String lessonTime;
    private String status;
}
