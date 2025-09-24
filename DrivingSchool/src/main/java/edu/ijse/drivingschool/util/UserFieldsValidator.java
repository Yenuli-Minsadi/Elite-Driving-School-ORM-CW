package edu.ijse.drivingschool.util;

import edu.ijse.drivingschool.dto.UserDTO;
import edu.ijse.drivingschool.exception.MissingFields;

public class UserFieldsValidator {

    public static void fieldsValidate(UserDTO userDTO) throws MissingFields {
        if (userDTO==null) {
            throw new MissingFields("User data is required");
        }

        if (isNullOrEmpty(userDTO.getUserId()) ||
                isNullOrEmpty(userDTO.getFirstName()) ||
                isNullOrEmpty(userDTO.getLastName()) ||
                isNullOrEmpty(userDTO.getUsername()) ||
                isNullOrEmpty(userDTO.getEmail()) ||
                isNullOrEmpty(userDTO.getPhone()) ||
                isNullOrEmpty(userDTO.getPassword()) ||
                isNullOrEmpty(userDTO.getRole()))
        {
            throw new MissingFields ("Fields cannot be empty, provide all user data.");
        }

    }

    private static boolean isNullOrEmpty(String field) {
        return field==null || field.isEmpty();
    }
}
