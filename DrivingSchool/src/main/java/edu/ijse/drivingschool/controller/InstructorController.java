package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.CourseBO;
import edu.ijse.drivingschool.bo.custom.InstructorBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dto.InstructorDTO;
import edu.ijse.drivingschool.dto.PaymentDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class InstructorController implements Initializable {

    @FXML
    private Pane ancStudentDash;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbAvailability;

    @FXML
    private Label lblInsId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFn;

    @FXML
    private TextField txtLn;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtSpecialization;

    InstructorBO instructorBO = (InstructorBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.INSTRUCTOR);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {
            InstructorDTO instructor = new InstructorDTO(
                    lblInsId.getText(),
                    txtFn.getText(),
                    txtLn.getText(),
                    txtSpecialization.getText(),
                    txtPhone.getText(),
                    txtEmail.getText(),
                    txtAddress.getText(),
                    cmbAvailability.getValue()
            );

            boolean isSaved = instructorBO.save(instructor);

            if (isSaved) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Instructor saved successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save instructor. Try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadNextId();
            cmbAvailability.getItems().addAll("Available", "Unavailable");
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    private void loadNextId() throws Exception {
        String nextId = instructorBO.getNextId();
        lblInsId.setText(nextId);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
