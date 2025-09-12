package edu.ijse.drivingschool.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorTM {
    private String instructorId;
    private String firstName;
    private String lastName;
    private String specialization;
    private int phone;
    private String email;
    private String address;
}
