package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.StudentBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.dto.tm.StudentTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

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
}
