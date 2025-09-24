package edu.ijse.drivingschool.util;

import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.exception.MissingFields;

public class StudentFieldsValidator {

    public static void fieldsValidate(StudentDTO studentDTO) throws MissingFields {
        if (studentDTO==null) {
            throw new MissingFields("Student data is required");
        }

        if (isNullOrEmpty(studentDTO.getStudentId()) ||
            isNullOrEmpty(studentDTO.getFirstName()) ||
            isNullOrEmpty(studentDTO.getLastName()) ||
            isNullOrEmpty(studentDTO.getEmail()) ||
            isNullOrEmpty(studentDTO.getPhone()) ||
            isNullOrEmpty(studentDTO.getAddress()) ||
            isNullOrEmpty(String.valueOf(studentDTO.getDob())))
        {
            throw new MissingFields ("Fields cannot be empty, provide all student data.");
        }

    }

    private static boolean isNullOrEmpty(String field) {
        return field==null || field.isEmpty();
    }
}
