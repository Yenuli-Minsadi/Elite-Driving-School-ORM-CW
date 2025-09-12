package edu.ijse.drivingschool.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTM {
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private int phone;
    private String password;
    private String role;
}
