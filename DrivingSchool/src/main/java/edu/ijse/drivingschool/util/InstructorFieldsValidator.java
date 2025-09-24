package edu.ijse.drivingschool.util;

import edu.ijse.drivingschool.dto.InstructorDTO;
import edu.ijse.drivingschool.exception.MissingFields;

public class InstructorFieldsValidator {

    public static void fieldsValidate(InstructorDTO instructorDTO) throws MissingFields {
        if (instructorDTO==null) {
            throw new MissingFields("Instructor data is required");
        }

        if (isNullOrEmpty(instructorDTO.getInstructorId()) ||
                isNullOrEmpty(instructorDTO.getFirstName()) ||
                isNullOrEmpty(instructorDTO.getLastName()) ||
                isNullOrEmpty(instructorDTO.getSpecialization()) ||
                isNullOrEmpty(instructorDTO.getPhone()) ||
                isNullOrEmpty(instructorDTO.getEmail()) ||
                isNullOrEmpty(instructorDTO.getAddress()) ||
                isNullOrEmpty(instructorDTO.getAvailability()))
        {
            throw new MissingFields ("Fields cannot be empty, provide all instructor data.");
        }

    }

    private static boolean isNullOrEmpty(String field) {
        return field==null || field.isEmpty();
    }
}
