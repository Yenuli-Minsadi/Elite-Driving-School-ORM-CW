package edu.ijse.drivingschool.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseTM {
    private String courseId;
    private String courseName;
    private String courseType;
    private String courseFee;
    private String duration;
}
