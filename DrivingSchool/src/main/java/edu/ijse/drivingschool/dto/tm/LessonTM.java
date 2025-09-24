package edu.ijse.drivingschool.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  LessonTM {
    private String lessonId;
    private String lessonName;
    private String lessonDescription;
    private String registrationId;
    private String instructorId;
    private String courseId;
    private LocalDate lessonDate;
    private String lessonTime;
    private String status;

    public LessonTM(String lessonId, String lessonName, String lessonDescription, String instructorId, String courseId, LocalDate lessonDate, String lessonTime, String status) {
        this.lessonId=lessonId;
        this.lessonName=lessonName;
        this.lessonDescription=lessonDescription;
        this.instructorId=instructorId;
        this.courseId=courseId;
        this.lessonDate=lessonDate;
        this.lessonTime=lessonTime;
        this.status=status;
    }
}
