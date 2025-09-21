package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.custom.UserBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dto.UserDTO;
import edu.ijse.drivingschool.dto.tm.UserTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {

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

    UserBO userBO = (UserBO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

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
}
