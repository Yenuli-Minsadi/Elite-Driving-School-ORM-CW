package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.InstructorBO;
import edu.ijse.drivingschool.dto.InstructorDTO;
import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.dto.tm.InstructorTM;
import edu.ijse.drivingschool.dto.tm.LessonTM;
import edu.ijse.drivingschool.dto.tm.StudentTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @FXML
    private TableColumn<InstructorTM, String> colAddress;

    @FXML
    private TableColumn<InstructorTM, String> colEmail;

    @FXML
    private TableColumn<InstructorTM, String> colFn;

    @FXML
    private TableColumn<InstructorTM, String> colId;

    @FXML
    private TableColumn<InstructorTM, String> colLn;

    @FXML
    private TableColumn<InstructorTM, String> colPhone;

    @FXML
    private TableColumn<InstructorTM, String> colSpecialize;

    @FXML
    private TableColumn<InstructorTM, String> colAvailability;

    @FXML
    private TableView<InstructorTM> tblInstructor;

    InstructorBO instructorBO = (InstructorBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.INSTRUCTOR);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        InstructorTM instructor = tblInstructor.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure ?",
                ButtonType.YES,
                ButtonType.NO
        );

        if (instructor == null) {
            showAlert(Alert.AlertType.WARNING, "Error", "Please select a student first!");
        }

        try {
            boolean isDeleted = instructorBO.delete(instructor.getInstructorId());

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
        InstructorTM instructorTM = tblInstructor.getSelectionModel().getSelectedItem();

        if (instructorTM == null) {
            showAlert(Alert.AlertType.WARNING, "Error", "Please select a student first!");
        }

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

            boolean isUpdated = instructorBO.update(instructor);

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
        tblInstructor.getItems().clear();
        ArrayList<InstructorDTO> instructorDTOArrayList = (ArrayList<InstructorDTO>) instructorBO.getAll();
        ObservableList<InstructorTM> instructorTMS = FXCollections.observableArrayList();

        for (InstructorDTO instructorDTO : instructorDTOArrayList) {
            InstructorTM instructorTM = new InstructorTM(
                    instructorDTO.getInstructorId(),
                    instructorDTO.getFirstName(),
                    instructorDTO.getLastName(),
                    instructorDTO.getSpecialization(),
                    instructorDTO.getPhone(),
                    instructorDTO.getEmail(),
                    instructorDTO.getAddress(),
                    instructorDTO.getAvailability()
            );
            instructorTMS.add(instructorTM);
        }
        tblInstructor.setItems(instructorTMS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colId.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colFn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colSpecialize.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));

        try {
            loadTableData();
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
            lblInsId.setText("");
            txtFn.setText("");
            txtLn.setText("");
            txtSpecialization.setText("");
            txtPhone.setText("");
            txtEmail.setText("");
            txtAddress.setText("");
            cmbAvailability.setValue(null);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        InstructorTM selectedRequest = tblInstructor.getSelectionModel().getSelectedItem();

        if (selectedRequest != null) {
            lblInsId.setText(selectedRequest.getInstructorId());
            txtFn.setText(selectedRequest.getFirstName());
            txtLn.setText(selectedRequest.getLastName());
            txtSpecialization.setText(selectedRequest.getLastName());
            txtPhone.setText(selectedRequest.getPhone());
            txtEmail.setText(selectedRequest.getEmail());
            txtAddress.setText(selectedRequest.getAddress());
            cmbAvailability.setValue(selectedRequest.getAvailability());

            // save button disable
            btnSave.setDisable(true);

            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
