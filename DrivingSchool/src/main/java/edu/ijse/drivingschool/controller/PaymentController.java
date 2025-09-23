package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.LessonBO;
import edu.ijse.drivingschool.bo.custom.PaymentBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dto.PaymentDTO;
import edu.ijse.drivingschool.dto.RegistrationDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
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
    private ComboBox<String> cmbStatus;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.PAYMENT);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

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

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
