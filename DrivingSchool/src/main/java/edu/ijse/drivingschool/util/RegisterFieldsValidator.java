package edu.ijse.drivingschool.util;

import edu.ijse.drivingschool.dto.RegistrationDTO;
import edu.ijse.drivingschool.exception.MissingFields;

public class RegisterFieldsValidator {

    public static void fieldsValidate(RegistrationDTO registrationDTO) throws MissingFields {
        if (registrationDTO==null) {
            throw new MissingFields("Registration data is required");
        }

        if (isNullOrEmpty(registrationDTO.getRegistrationId()) ||
                isNullOrEmpty(registrationDTO.getStudentId()) ||
                isNullOrEmpty(registrationDTO.getCourseId()) ||
                isNullOrEmpty(String.valueOf(registrationDTO.getRegistrationDate())))
        {
            throw new MissingFields ("Fields cannot be empty, provide all registration data.");
        }
    }

    private static boolean isNullOrEmpty(String field) {
        return field==null || field.isEmpty();
    }

}
