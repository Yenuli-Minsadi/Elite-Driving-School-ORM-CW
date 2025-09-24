package edu.ijse.drivingschool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashController {

    @FXML
    private Button btnCoordinatoor;

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
    private Label lblLogout;



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
    void btnCoordinatorOnAction(ActionEvent event) throws IOException {
        ancMainDash.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/Coordinator.fxml"));

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

    @FXML
    void onLogoutClick(MouseEvent event) {
        // Create a confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        // Show the alert and wait for user's response
        alert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                try {
                    // Load the Login.fxml
                    Parent loginParent = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
                    Scene loginScene = new Scene(loginParent);

                    // Get the current stage (window)
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    // Set the login scene
                    window.setScene(loginScene);
                    window.centerOnScreen(); // Optional: center the login page
                    window.show();
                } catch (IOException e) {
                    new Alert(Alert.AlertType.ERROR, "Unable to load Login page!").show();
                    e.printStackTrace();
                }
            }
            // If the user presses CANCEL, do nothing (stay on the dashboard)
        });
    }

}
