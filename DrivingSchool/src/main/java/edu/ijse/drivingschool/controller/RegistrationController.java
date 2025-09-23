package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.PaymentBO;
import edu.ijse.drivingschool.bo.custom.RegistrationBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dto.RegistrationDTO;
import edu.ijse.drivingschool.dto.StudentDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

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
    private DatePicker dateRegDate;

    @FXML
    private Label lblRegId;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtSudentId;

    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.REGISTRATION);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {

            RegistrationDTO registration = new RegistrationDTO(
                    lblRegId.getText(),
                    txtSudentId.getText(),
                    txtCourseId.getText(),
                    dateRegDate.getValue()
            );

            boolean isSaved = registrationBO.save(registration);

            if (isSaved) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Registration saved successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save registration. Try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    private void loadNextId() throws Exception {
        String nextId = registrationBO.getNextId();
        lblRegId.setText(nextId);
    }
}
