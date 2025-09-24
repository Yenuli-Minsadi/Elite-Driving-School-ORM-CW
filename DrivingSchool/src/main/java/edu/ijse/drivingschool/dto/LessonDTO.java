package edu.ijse.drivingschool.dto;

import edu.ijse.drivingschool.entity.Course;
import edu.ijse.drivingschool.entity.Instructor;
import edu.ijse.drivingschool.entity.Registration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {
    private String lessonId;
    private String lessonName;
    private String lessonDescription;
    private String registrationId;
    private String instructorId;
    private String courseId;
    private LocalDate lessonDate;
    private String lessonTime;
    private String status;

    public LessonDTO(String lessonId, String lessonName, String lessonDescription, Registration registration, Instructor instructor, Course course, LocalDate lessonDate, String lessonTime, String status) {
        this.lessonId=lessonId;
        this.lessonName=lessonName;
        this.lessonDescription=lessonDescription;
        this.registrationId=registration.getRegistrationId();
        this.instructorId=instructor.getInstructorId();
        this.courseId=course.getCourseId();
        this.lessonDate=lessonDate;
        this.lessonTime=lessonTime;
        this.status=status;
    }
}
