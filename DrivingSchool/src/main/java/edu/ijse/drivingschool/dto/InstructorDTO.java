package edu.ijse.drivingschool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDTO {
    private String instructorId;
    private String firstName;
    private String lastName;
    private String specialization;
    private String phone;
    private String email;
    private String address;
    private String availability;
}
