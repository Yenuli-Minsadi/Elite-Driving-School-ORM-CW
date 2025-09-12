package edu.ijse.drivingschool.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentTM {
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private String address;
    private LocalDate dob;
}
