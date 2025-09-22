package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.StudentBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.dto.UserDTO;
import edu.ijse.drivingschool.dto.tm.StudentTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController {

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
    private DatePicker dateDOB;

    @FXML
    private Label lblSid;

    @FXML
    private TableView<StudentTM> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPhone;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.STUDENT);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {

            StudentDTO student = new StudentDTO(
                    lblSid.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    txtAddress.getText(),
                    dateDOB.getValue()
            );

            boolean isSaved = studentBO.save(student);

            if (isSaved) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Student saved successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save student. Try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        tblStudent.getItems().clear();
        ArrayList<StudentDTO> studentsDtoArrayList = (ArrayList<StudentDTO>) studentBO.getAll();
        ObservableList<StudentTM> studentTMS = FXCollections.observableArrayList();

        for (StudentDTO studentDTO : studentsDtoArrayList) {
            StudentTM studentTM = new StudentTM(
                    studentDTO.getStudentId(),
                    studentDTO.getFirstName(),
                    studentDTO.getLastName(),
                    studentDTO.getEmail(),
                    studentDTO.getPhone(),
                    studentDTO.getAddress(),
                    studentDTO.getDob()
            );
            studentTMS.add(studentTM);
        }
        tblStudent.setItems(studentTMS);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
