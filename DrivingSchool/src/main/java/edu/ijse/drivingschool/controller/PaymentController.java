package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.LessonBO;
import edu.ijse.drivingschool.bo.custom.PaymentBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dto.PaymentDTO;
import edu.ijse.drivingschool.dto.RegistrationDTO;
import edu.ijse.drivingschool.dto.tm.PaymentTM;
import edu.ijse.drivingschool.dto.tm.RegistrationTM;
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

public class PaymentController implements Initializable {

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
    private TableColumn<PaymentTM, String> colAmount;

    @FXML
    private TableColumn<PaymentTM, String> colDate;

    @FXML
    private TableColumn<PaymentTM, String> colPid;

    @FXML
    private TableColumn<PaymentTM, String> colPmethod;

    @FXML
    private TableColumn<PaymentTM, String> colPtype;

    @FXML
    private TableColumn<PaymentTM, String> colRid;

    @FXML
    private TableColumn<PaymentTM, String> colStatus;

    @FXML
    private ComboBox<String> cmbMethod;

    @FXML
    private ComboBox<String> cmbPtype;

    @FXML
    private DatePicker datePdate;

    @FXML
    private Label lblPid;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtRid;

    @FXML
    private TableView<PaymentTM> tblPayment;

    @FXML
    private ComboBox<String> cmbStatus;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.PAYMENT);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        PaymentTM payment = tblPayment.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure ?",
                ButtonType.YES,
                ButtonType.NO
        );

        if (payment == null) {
            showAlert(Alert.AlertType.WARNING, "Error", "Please select a payment first!");
        }

        try {
            boolean isDeleted = paymentBO.delete(payment.getPaymentId());

            if (isDeleted) {
                resetPage();
                loadNextId();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Payment deleted successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete payment. Try again.");
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

            PaymentDTO payment = new PaymentDTO(
                    lblPid.getText(),
                    txtRid.getText(),
                    cmbPtype.getValue(),
                    cmbMethod.getValue(),
                    txtAmount.getText(),
                    datePdate.getValue(),
                    cmbStatus.getValue()
            );

            boolean isSaved = paymentBO.save(payment);

            if (isSaved) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Payment saved successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save payment. Try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        PaymentTM paymentTM = tblPayment.getSelectionModel().getSelectedItem();

        if (paymentTM == null) {
            showAlert(Alert.AlertType.WARNING, "Error", "Please select a payment first!");
        }

        try {

            PaymentDTO payment = new PaymentDTO(
                    lblPid.getText(),
                    txtRid.getText(),
                    cmbPtype.getValue(),
                    cmbMethod.getValue(),
                    txtAmount.getText(),
                    datePdate.getValue(),
                    cmbStatus.getValue()
            );

            boolean isUpdated = paymentBO.update(payment);

            if (isUpdated) {
                resetPage();
                loadNextId();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Payment updated successfully!");

            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update payment. Try again.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colPid.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colRid.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        colPtype.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        colPmethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("paymentAmount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            loadTableData();
            loadNextId();
            cmbMethod.getItems().addAll("Cash", "Card","Online Transfer");
            cmbPtype.getItems().addAll("Upfront", "Installment", "Full");
            cmbStatus.getItems().addAll("On Going", "Completed");
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    private void loadNextId() throws Exception {
        String nextId = paymentBO.getNextId();
        lblPid.setText(nextId);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        tblPayment.getItems().clear();
        ArrayList<PaymentDTO> paymentDTOArrayList= (ArrayList<PaymentDTO>) paymentBO.getAll();
        ObservableList<PaymentTM> paymentTMS = FXCollections.observableArrayList();

        for (PaymentDTO paymentDTO : paymentDTOArrayList) {
            PaymentTM paymentTM = new PaymentTM(
                    paymentDTO.getPaymentId(),
                    paymentDTO.getRegistrationId(),
                    paymentDTO.getPaymentType(),
                    paymentDTO.getPaymentMethod(),
                    paymentDTO.getPaymentAmount(),
                    paymentDTO.getPaymentDate(),
                    paymentDTO.getStatus()
            );
            paymentTMS.add(paymentTM);
        }
        tblPayment.setItems(paymentTMS);
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
            lblPid.setText("");
            txtRid.setText("");
            cmbPtype.setValue(null);
            cmbMethod.setValue(null);
            txtAmount.setText("");
            datePdate.setValue(null);
            cmbStatus.setValue(null);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        PaymentTM selectedRequest = tblPayment.getSelectionModel().getSelectedItem();

        if (selectedRequest != null) {
            lblPid.setText(selectedRequest.getPaymentId());
            txtRid.setText(selectedRequest.getRegistrationId());
            cmbPtype.setValue(selectedRequest.getPaymentType());
            cmbPtype.setValue(selectedRequest.getPaymentMethod());
            txtAmount.setText(selectedRequest.getPaymentAmount());
            datePdate.setValue(selectedRequest.getPaymentDate());
            cmbStatus.setValue(selectedRequest.getStatus());

            // save button disable
            btnSave.setDisable(true);

            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
