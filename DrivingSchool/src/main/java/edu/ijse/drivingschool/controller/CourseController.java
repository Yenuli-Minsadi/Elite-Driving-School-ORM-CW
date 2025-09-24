package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.CourseBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dto.CourseDTO;
import edu.ijse.drivingschool.dto.RegistrationDTO;
import edu.ijse.drivingschool.dto.StudentDTO;
import edu.ijse.drivingschool.dto.tm.CourseTM;
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

public class CourseController implements Initializable {

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
    private Label lblCourseId;

    @FXML
    private TextField txtCName;

    @FXML
    private TextField txtCdescription;

    @FXML
    private TextField txtCduration;

    @FXML
    private TextField txtCfee;

    @FXML
    private TextField txtCtype;

    @FXML
    private TableColumn<CourseTM, String> colCfee;

    @FXML
    private TableColumn<CourseTM, String> colCname;

    @FXML
    private TableColumn<CourseTM, String> colDesc;

    @FXML
    private TableColumn<CourseTM, String> colCtype;

    @FXML
    private TableColumn<CourseTM, String> colDuration;

    @FXML
    private TableColumn<CourseTM, String> colId;

    @FXML
    private TableView<CourseTM> tblCourse;


    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.COURSE);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        CourseTM course = tblCourse.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure ?",
                ButtonType.YES,
                ButtonType.NO
        );

        if (course == null) {
            showAlert(Alert.AlertType.WARNING, "Error", "Please select a student first!");
        }

        try {
            boolean isDeleted = courseBO.delete(course.getCourseId());

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

            CourseDTO course = new CourseDTO(
                    lblCourseId.getText(),
                    txtCName.getText(),
                    txtCdescription.getText(),
                    txtCtype.getText(),
                    txtCfee.getText(),
                    txtCduration.getText()
            );

            boolean isSaved = courseBO.save(course);

            if (isSaved) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Course saved successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save course. Try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        CourseTM courseTM = tblCourse.getSelectionModel().getSelectedItem();

        if (courseTM == null) {
            showAlert(Alert.AlertType.WARNING, "Error", "Please select a student first!");
        }

        try {

            CourseDTO course = new CourseDTO(
                    lblCourseId.getText(),
                    txtCName.getText(),
                    txtCdescription.getText(),
                    txtCtype.getText(),
                    txtCfee.getText(),
                    txtCduration.getText()
            );

            boolean isUpdated = courseBO.update(course);

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
        tblCourse.getItems().clear();
        ArrayList<CourseDTO> courseDTOArrayList = (ArrayList<CourseDTO>) courseBO.getAll();
        ObservableList<CourseTM> courseTMS = FXCollections.observableArrayList();

        for (CourseDTO courseDTO : courseDTOArrayList) {
            CourseTM courseTM = new CourseTM(
                    courseDTO.getCourseId(),
                    courseDTO.getCourseName(),
                    courseDTO.getCourseDescription(),
                    courseDTO.getCourseType(),
                    courseDTO.getCourseFee(),
                    courseDTO.getDuration()
            );
            courseTMS.add(courseTM);
        }
        tblCourse.setItems(courseTMS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCname.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCtype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colCfee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));

        try {
            loadTableData();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    private void loadNextId() throws Exception {
        String nextId = courseBO.getNextId();
        lblCourseId.setText(nextId);
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
            lblCourseId.setText("");
            txtCName.setText("");
            txtCdescription.setText("");
            txtCtype.setText("");
            txtCfee.setText("");
            txtCduration.setText("");


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        CourseTM selectedRequest = tblCourse.getSelectionModel().getSelectedItem();

        if (selectedRequest != null) {
            lblCourseId.setText(selectedRequest.getCourseId());
            txtCName.setText(selectedRequest.getCourseName());
            txtCdescription.setText(selectedRequest.getCourseDescription());
            txtCtype.setText(selectedRequest.getCourseType());
            txtCfee.setText(selectedRequest.getCourseFee());
            txtCduration.setText(selectedRequest.getDuration());

            // save button disable
            btnSave.setDisable(true);

            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
