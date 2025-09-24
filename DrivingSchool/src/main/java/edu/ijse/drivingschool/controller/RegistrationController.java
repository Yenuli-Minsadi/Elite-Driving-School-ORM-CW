package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.PaymentBO;
import edu.ijse.drivingschool.bo.custom.RegistrationBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dto.RegistrationDTO;
import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.dto.tm.RegistrationTM;
import edu.ijse.drivingschool.dto.tm.StudentTM;
import edu.ijse.drivingschool.entity.Registration;
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

    @FXML
    private TableView<RegistrationTM> tblReg;

    @FXML
    private TableColumn<RegistrationTM, String> colCid;

    @FXML
    private TableColumn<RegistrationTM, String> colRegDate;

    @FXML
    private TableColumn<RegistrationTM, String> colRegId;

    @FXML
    private TableColumn<RegistrationTM, String> colSid;


    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.REGISTRATION);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        RegistrationTM registration = tblReg.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure ?",
                ButtonType.YES,
                ButtonType.NO
        );

        if (registration == null) {
            showAlert(Alert.AlertType.WARNING, "Error", "Please select a registration first!");
        }

        try {
            boolean isDeleted = registrationBO.delete(registration.getRegistrationId());

            if (isDeleted) {
                resetPage();
                loadNextId();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Registration deleted successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete registration. Try again.");
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
        RegistrationTM registrationTM = tblReg.getSelectionModel().getSelectedItem();

        if (registrationTM == null) {
            showAlert(Alert.AlertType.WARNING, "Error", "Please select a student first!");
        }

        try {

            RegistrationDTO registration = new RegistrationDTO(
                    lblRegId.getText(),
                    txtSudentId.getText(),
                    txtCourseId.getText(),
                    dateRegDate.getValue()
            );

            boolean isUpdated = registrationBO.update(registration);

            if (isUpdated) {
                resetPage();
                loadNextId();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Registration updated successfully!");

            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update registration. Try again.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }
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

        colRegId.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        colSid.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colCid.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

        try {
            loadTableData();
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

    private void loadTableData() throws SQLException, ClassNotFoundException {
        tblReg.getItems().clear();
        ArrayList<RegistrationDTO> registrationDTOArrayList = (ArrayList<RegistrationDTO>) registrationBO.getAll();
        ObservableList<RegistrationTM> registrationTMS = FXCollections.observableArrayList();

        for (RegistrationDTO registrationDTO : registrationDTOArrayList) {
            RegistrationTM registrationTM = new RegistrationTM(
                    registrationDTO.getRegistrationId(),
                    registrationDTO.getStudentId(),
                    registrationDTO.getCourseId(),
                    registrationDTO.getRegistrationDate()
            );
            registrationTMS.add(registrationTM);
        }
        tblReg.setItems(registrationTMS);
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
            lblRegId.setText("");
            txtSudentId.setText("");
            txtCourseId.setText("");
            dateRegDate.setValue(null);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        RegistrationTM selectedRequest = tblReg.getSelectionModel().getSelectedItem();

        if (selectedRequest != null) {
            lblRegId.setText(selectedRequest.getRegistrationId());
            txtSudentId.setText(selectedRequest.getStudentId());
            txtCourseId.setText(selectedRequest.getCourseId());
            dateRegDate.setValue(selectedRequest.getRegistrationDate());

            // save button disable
            btnSave.setDisable(true);

            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
