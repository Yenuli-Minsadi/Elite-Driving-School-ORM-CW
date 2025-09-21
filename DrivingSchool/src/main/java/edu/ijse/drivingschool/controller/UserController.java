package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.UserBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dto.UserDTO;
import edu.ijse.drivingschool.dto.tm.UserTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.mindrot.jbcrypt.BCrypt;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserController implements Initializable {

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
    private ComboBox<String> cmbRole;

    @FXML
    private Label lblUid;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TextField txtConPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtUsnm;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USER);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {

            String hashedPassword = BCrypt.hashpw(txtPassword.getText(), BCrypt.gensalt());

            UserDTO newUser = new UserDTO(
                    lblUid.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtUsnm.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    hashedPassword,
                    cmbRole.getValue()
            );

            boolean isSaved = userBO.save(newUser);

            if (isSaved) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "User saved successfully!");
//                loadNextId();
//                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save user. Try again.");
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
        tblUser.getItems().clear();
        ArrayList<UserDTO> usersDtoArrayList = (ArrayList<UserDTO>) userBO.getAll();
        ObservableList<UserTM> userTMS = FXCollections.observableArrayList();

        for (UserDTO userDTO : usersDtoArrayList) {
            UserTM userTM = new UserTM(
                    userDTO.getUserId(),
                    userDTO.getFirstName(),
                    userDTO.getLastName(),
                    userDTO.getUsername(),
                    userDTO.getEmail(),
                    userDTO.getPhone(),
                    userDTO.getPassword(),
                    userDTO.getRole()
            );
            userTMS.add(userTM);
        }
        tblUser.setItems(userTMS);
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
            cmbRole.getItems().addAll("admin", "receptionist");
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    private void loadNextId() throws Exception {
        String nextId = userBO.getNextId();
        lblUid.setText(nextId);
    }
}
