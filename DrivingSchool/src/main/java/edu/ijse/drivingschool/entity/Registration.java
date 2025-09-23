package edu.ijse.drivingschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "registration ")
public class Registration {
    @Id
    private String registrationId;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    private LocalDate registrationDate;

    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL)
    private List<Payment> payment;

    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL)
    private List<Lesson> lesson;

    public Registration(String registrationId, Student studentId, Course courseId, LocalDate registrationDate) {
        this.registrationId = registrationId;
        this.student = studentId;   // assign actual Student object
        this.course = courseId;     // assign actual Course object
        this.registrationDate = registrationDate;
    }

}
