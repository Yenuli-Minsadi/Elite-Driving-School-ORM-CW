package edu.ijse.drivingschool.dto;

import edu.ijse.drivingschool.entity.Course;
import edu.ijse.drivingschool.entity.Registration;
import edu.ijse.drivingschool.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    private String registrationId;
    private String studentId;
    private String courseId;
    private LocalDate registrationDate;

    public RegistrationDTO(String registrationId, Student student, Course course, LocalDate registrationDate) {
        this.registrationId=registrationId;
        this.studentId= student.getStudentId();
        this.courseId= course.getCourseId();
        this.registrationDate=registrationDate;
    }

}
