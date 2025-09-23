package edu.ijse.drivingschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "instructor")
public class Instructor {
    @Id
    private String instructorId;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<Lesson> lesson;

    private String specialization;
    private String phone;
    private String email;
    private String address;
    private String availability;

    public Instructor(String instructorId, String firstName, String lastName, String specialization, String phone, String email, String address, String availability) {
        this.instructorId=instructorId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.specialization=specialization;
        this.phone=phone;
        this.email=email;
        this.address=address;
        this.availability=availability;
    }
}
