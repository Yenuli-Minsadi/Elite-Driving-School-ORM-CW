package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.StudentBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.dto.UserDTO;
import edu.ijse.drivingschool.dto.tm.StudentTM;
import edu.ijse.drivingschool.dto.tm.UserTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.mindrot.jbcrypt.BCrypt;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

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

    @FXML
    private TableColumn<StudentTM, String> colAddress;

    @FXML
    private TableColumn<StudentTM, String> colDOB;

    @FXML
    private TableColumn<StudentTM, String> colEmail;

    @FXML
    private TableColumn<StudentTM, String> colFn;

    @FXML
    private TableColumn<StudentTM, String> colId;

    @FXML
    private TableColumn<StudentTM, String> colLn;

    @FXML
    private TableColumn<StudentTM, String> colPhone;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.STUDENT);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        StudentTM student = tblStudent.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure ?",
                ButtonType.YES,
                ButtonType.NO
        );

        if (student == null) {
            showAlert(Alert.AlertType.WARNING, "Error", "Please select a student first!");
        }

        try {
            boolean isDeleted = studentBO.delete(student.getStudentId());

            if (isDeleted) {
                resetPage();
                loadNextId();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Student deleted successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete student. Try again.");
            }

        } catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        resetPage();
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
                resetPage();
                loadNextId();
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
        StudentTM studentTM = tblStudent.getSelectionModel().getSelectedItem();

        if (studentTM == null) {
            showAlert(Alert.AlertType.WARNING, "Error", "Please select a student first!");
        }

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

            boolean isUpdated = studentBO.update(student);

            if (isUpdated) {
                resetPage();
                loadNextId();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Student updated successfully!");

            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update student. Try again.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colFn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));

        try {
            loadTableData();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    private void loadNextId() throws Exception {
        String nextId = studentBO.getNextId();
        lblSid.setText(nextId);
    }

    private void resetPage() {
        try {
            loadTableData();
            loadNextId();

            // save button (id) -> enable
            btnSave.setDisable(false);

            // update, delete button (id) -> disable
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

//            lblRequestId.setText("");
            lblSid.setText("");
            txtFirstName.setText("");
            txtLastName.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtAddress.setText("");
            dateDOB.setValue(null);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        StudentTM selectedRequest = tblStudent.getSelectionModel().getSelectedItem();

        if (selectedRequest != null) {
            lblSid.setText(selectedRequest.getStudentId());
            txtFirstName.setText(selectedRequest.getFirstName());
            txtLastName.setText(selectedRequest.getLastName());
            txtEmail.setText(selectedRequest.getEmail());
            txtPhone.setText(selectedRequest.getPhone());
            txtAddress.setText(selectedRequest.getAddress());
            dateDOB.setValue(selectedRequest.getDob());

            // save button disable
            btnSave.setDisable(true);

            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
