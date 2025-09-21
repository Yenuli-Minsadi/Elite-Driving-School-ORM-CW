package edu.ijse.drivingschool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatorDTO {
    private String coordinatorId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
}
