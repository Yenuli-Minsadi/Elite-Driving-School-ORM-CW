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

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List <Lesson> lesson;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Registration> registration;


    private String courseType;
    private String courseFee;
    private String duration;
}
