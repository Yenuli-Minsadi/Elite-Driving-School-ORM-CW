package edu.ijse.drivingschool.util;

import edu.ijse.drivingschool.dto.CourseDTO;
import edu.ijse.drivingschool.exception.MissingFields;

public class CourseFieldsValidator {

    public static void fieldsValidate(CourseDTO courseDTO) throws MissingFields {
        if (courseDTO==null) {
            throw new MissingFields("Course data is required");
        }

        if (isNullOrEmpty(courseDTO.getCourseId()) ||
                isNullOrEmpty(courseDTO.getCourseName()) ||
                isNullOrEmpty(courseDTO.getCourseDescription()) ||
                isNullOrEmpty(courseDTO.getCourseType()) ||
                isNullOrEmpty(courseDTO.getCourseFee()) ||
                isNullOrEmpty(courseDTO.getDuration()))
        {
            throw new MissingFields ("Fields cannot be empty, provide all course data.");
        }

    }

    private static boolean isNullOrEmpty(String field) {
        return field==null || field.isEmpty();
    }
}
