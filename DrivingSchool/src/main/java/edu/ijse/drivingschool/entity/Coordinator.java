package edu.ijse.drivingschool.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coordinator")
public class Coordinator {
    @Id
    private String coordinatorId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
}
