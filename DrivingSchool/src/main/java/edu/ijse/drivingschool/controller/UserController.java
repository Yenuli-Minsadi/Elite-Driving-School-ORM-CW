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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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

    @FXML
    private TableColumn<UserTM, String> colEmail;

    @FXML
    private TableColumn<UserTM, String> colFn;

    @FXML
    private TableColumn<UserTM, String> colLn;

    @FXML
    private TableColumn<UserTM, String> colPhone;

    @FXML
    private TableColumn<UserTM, String> colPwd;

    @FXML
    private TableColumn<UserTM, String> colRole;

    @FXML
    private TableColumn<UserTM, String> colUid;

    @FXML
    private TableColumn<UserTM, String> colUsnm;



    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USER);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        UserTM user = tblUser.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure ?",
                ButtonType.YES,
                ButtonType.NO
        );

        if (user == null) {
            showAlert(Alert.AlertType.WARNING, "Error", "Please select a user first!");
        }

        try {
            boolean isDeleted = userBO.delete(user.getUserId());

            if (isDeleted) {
                resetPage();
                loadNextId();
                showAlert(Alert.AlertType.INFORMATION, "Success", "User deleted successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete user. Try again.");
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
                resetPage();
                loadNextId();
                showAlert(Alert.AlertType.INFORMATION, "Success", "User saved successfully!");

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
        UserTM user = tblUser.getSelectionModel().getSelectedItem();

        if (user == null) {
            showAlert(Alert.AlertType.WARNING, "Error", "Please select a user first!");
        }

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

            boolean isUpdated = userBO.update(newUser);

            if (isUpdated) {
                resetPage();
                loadNextId();
                showAlert(Alert.AlertType.INFORMATION, "Success", "User updated successfully!");

            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update user. Try again.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }
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
        colUid.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colFn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colUsnm.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colPwd.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        try {
            loadTableData();
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
            lblUid.setText("");
            txtFirstName.setText("");
            txtLastName.setText("");
            txtUsnm.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtPassword.setText("");
            cmbRole.setValue(null);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        UserTM selectedRequest = tblUser.getSelectionModel().getSelectedItem();

        if (selectedRequest != null) {
            lblUid.setText(selectedRequest.getUserId());
            txtFirstName.setText(selectedRequest.getFirstName());
            txtLastName.setText(selectedRequest.getLastName());
            txtUsnm.setText(selectedRequest.getUsername());
            txtEmail.setText(selectedRequest.getEmail());
            txtPhone.setText(selectedRequest.getPhone());
            txtPassword.setText(selectedRequest.getPassword());
            cmbRole.setValue(selectedRequest.getRole());

            // save button disable
            btnSave.setDisable(true);

            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
