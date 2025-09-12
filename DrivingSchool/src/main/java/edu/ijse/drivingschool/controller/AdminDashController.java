package edu.ijse.drivingschool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AdminDashController {

    @FXML
    private Button btnConsult;

    @FXML
    private Button btnStudent;

    @FXML
    private Button btnCourse;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnInstructor;

    @FXML
    private Button btnLesson;


    @FXML
    private Button btnUser;


    @FXML
    private Pane ancMainDash;
    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        ancMainDash.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/Student.fxml"));

        pane.prefWidthProperty().bind(ancMainDash.widthProperty());
        pane.prefHeightProperty().bind(ancMainDash.heightProperty());

        ancMainDash.getChildren().add(pane);
    }

    @FXML
    void btnConsultOnAction(ActionEvent event) throws IOException {
        ancMainDash.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/Consult.fxml"));

        pane.prefWidthProperty().bind(ancMainDash.widthProperty());
        pane.prefHeightProperty().bind(ancMainDash.heightProperty());

        ancMainDash.getChildren().add(pane);
    }

    @FXML
    void btnCourseOnAction(ActionEvent event) throws IOException {
        ancMainDash.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/Course.fxml"));

        pane.prefWidthProperty().bind(ancMainDash.widthProperty());
        pane.prefHeightProperty().bind(ancMainDash.heightProperty());

        ancMainDash.getChildren().add(pane);
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
        ancMainDash.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/Registration.fxml"));

        pane.prefWidthProperty().bind(ancMainDash.widthProperty());
        pane.prefHeightProperty().bind(ancMainDash.heightProperty());

        ancMainDash.getChildren().add(pane);
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        ancMainDash.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/Payment.fxml"));

        pane.prefWidthProperty().bind(ancMainDash.widthProperty());
        pane.prefHeightProperty().bind(ancMainDash.heightProperty());

        ancMainDash.getChildren().add(pane);
    }

    @FXML
    void btnInstructorOnAction(ActionEvent event) throws IOException {
        ancMainDash.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/Instructor.fxml"));

        pane.prefWidthProperty().bind(ancMainDash.widthProperty());
        pane.prefHeightProperty().bind(ancMainDash.heightProperty());

        ancMainDash.getChildren().add(pane);
    }

    @FXML
    void btnLessonOnAction(ActionEvent event) throws IOException {
        ancMainDash.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/Lesson.fxml"));

        pane.prefWidthProperty().bind(ancMainDash.widthProperty());
        pane.prefHeightProperty().bind(ancMainDash.heightProperty());

        ancMainDash.getChildren().add(pane);
    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        ancMainDash.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/User.fxml"));

        pane.prefWidthProperty().bind(ancMainDash.widthProperty());
        pane.prefHeightProperty().bind(ancMainDash.heightProperty());

        ancMainDash.getChildren().add(pane);
    }

}
