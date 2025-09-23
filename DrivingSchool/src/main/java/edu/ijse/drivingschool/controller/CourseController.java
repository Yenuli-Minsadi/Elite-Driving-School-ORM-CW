package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.CourseBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dto.CourseDTO;
import edu.ijse.drivingschool.dto.RegistrationDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseController implements Initializable {

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
    private Label lblCourseId;

    @FXML
    private TextField txtCName;

    @FXML
    private TextField txtCdescription;

    @FXML
    private TextField txtCduration;

    @FXML
    private TextField txtCfee;

    @FXML
    private TextField txtCtype;

    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.COURSE);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {

            CourseDTO course = new CourseDTO(
                    lblCourseId.getText(),
                    txtCName.getText(),
                    txtCdescription.getText(),
                    txtCtype.getText(),
                    txtCfee.getText(),
                    txtCduration.getText()
            );

            boolean isSaved = courseBO.save(course);

            if (isSaved) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Course saved successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save course. Try again.");
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
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    private void loadNextId() throws Exception {
        String nextId = courseBO.getNextId();
        lblCourseId.setText(nextId);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
