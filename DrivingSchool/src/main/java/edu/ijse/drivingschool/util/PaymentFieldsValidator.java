package edu.ijse.drivingschool.util;

import edu.ijse.drivingschool.dto.PaymentDTO;
import edu.ijse.drivingschool.exception.MissingFields;

public class PaymentFieldsValidator {

    public static void fieldsValidate(PaymentDTO paymentDTO) throws MissingFields {
        if (paymentDTO==null) {
            throw new MissingFields("Payment data is required");
        }

        if (isNullOrEmpty(paymentDTO.getPaymentId()) ||
                isNullOrEmpty(paymentDTO.getRegistrationId()) ||
                isNullOrEmpty(paymentDTO.getPaymentType()) ||
                isNullOrEmpty(paymentDTO.getPaymentMethod()) ||
                isNullOrEmpty(paymentDTO.getPaymentAmount()) ||
                isNullOrEmpty(String.valueOf(paymentDTO.getPaymentDate())) ||
                isNullOrEmpty(paymentDTO.getStatus()))
        {
            throw new MissingFields ("Fields cannot be empty, provide all payment data.");
        }

    }

    private static boolean isNullOrEmpty(String field) {
        return field==null || field.isEmpty();
    }
}
