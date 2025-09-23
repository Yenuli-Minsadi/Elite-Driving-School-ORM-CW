package edu.ijse.drivingschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
public class Course {
    @Id
    private String courseId;
    private String courseName;
    private String description;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Registration> registration;

    private String courseType;
    private String courseFee;
    private String duration;

    public Course(String courseId, String courseName, String courseDescription, String courseType, String courseFee, String duration) {
        this.courseId=courseId;
        this.courseName=courseName;
        this.description=courseDescription;
        this.courseType=courseType;
        this.courseFee=courseFee;
        this.duration=duration;
    }
}
