package edu.ijse.drivingschool.util;

import edu.ijse.drivingschool.dto.LessonDTO;
import edu.ijse.drivingschool.exception.MissingFields;

public class LessonFieldsValidator {

    public static void fieldsValidate(LessonDTO lessonDTO) throws MissingFields {
        if (lessonDTO==null) {
            throw new MissingFields("Lesson data is required");
        }

        if (isNullOrEmpty(lessonDTO.getLessonId()) ||
                isNullOrEmpty(lessonDTO.getLessonName()) ||
                isNullOrEmpty(lessonDTO.getLessonDescription()) ||
                isNullOrEmpty(lessonDTO.getRegistrationId()) ||
                isNullOrEmpty(lessonDTO.getInstructorId()) ||
                isNullOrEmpty(lessonDTO.getCourseId()) ||
                isNullOrEmpty(String.valueOf(lessonDTO.getLessonDate())) ||
                isNullOrEmpty(lessonDTO.getLessonTime()) ||
                isNullOrEmpty(lessonDTO.getStatus()))
        {
            throw new MissingFields ("Fields cannot be empty, provide all lesson data.");
        }

    }

    private static boolean isNullOrEmpty(String field) {
        return field==null || field.isEmpty();
    }
}
