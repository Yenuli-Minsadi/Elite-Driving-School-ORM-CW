package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.InstructorBO;
import edu.ijse.drivingschool.bo.custom.LessonBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dto.LessonDTO;
import edu.ijse.drivingschool.dto.PaymentDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class LessonController implements Initializable {

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
    private ComboBox<String> cmbStatus;

    @FXML
    private DatePicker dateLdate;

    @FXML
    private Label lblLessonId;

    @FXML
    private TextField txtCid;

    @FXML
    private TextField txtIid;

    @FXML
    private TextField txtLtime;

    @FXML
    private TextField txtRid;

    @FXML
    private TextField txtLname;

    @FXML
    private TextField txtDesc;

    LessonBO lessonBO = (LessonBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.LESSON);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {

            LessonDTO lesson = new LessonDTO(
                    lblLessonId.getText(),
                    txtLname.getText(),
                    txtDesc.getText(),
                    txtRid.getText(),
                    txtIid.getText(),
                    dateLdate.getValue(),
                    txtLtime.getText(),
                    cmbStatus.getValue()
            );

            boolean isSaved = lessonBO.save(lesson);

            if (isSaved) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Lesson saved successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save lesson. Try again.");
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
            cmbStatus.getItems().addAll("On Going", "Completed","New");

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    private void loadNextId() throws Exception {
        String nextId = lessonBO.getNextId();
        lblLessonId.setText(nextId);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
