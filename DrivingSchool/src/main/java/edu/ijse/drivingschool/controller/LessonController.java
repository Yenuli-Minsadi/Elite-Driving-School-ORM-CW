package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.InstructorBO;
import edu.ijse.drivingschool.bo.custom.LessonBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dto.LessonDTO;
import edu.ijse.drivingschool.dto.PaymentDTO;
import edu.ijse.drivingschool.dto.StudentDTO;
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

public class LessonController implements Initializable {

    @FXML
    private Pane ancStudentDash;

    @FXML
    private TableView<LessonTM> tblLesson;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private DatePicker dateLdate;

    @FXML
    private Label lblLessonId;

    @FXML
    private TextField txtCid;

    @FXML
    private TextField txtIid;

    @FXML
    private TextField txtLtime;

    @FXML
    private TextField txtRid;

    @FXML
    private TextField txtLname;

    @FXML
    private TextField txtDesc;


    @FXML
    private TableColumn<LessonTM, String> colDesc;

    @FXML
    private TableColumn<LessonTM, String> colCid;

    @FXML
    private TableColumn<LessonTM, String> colIid;

    @FXML
    private TableColumn<LessonTM, String> colLdate;

    @FXML
    private TableColumn<LessonTM, String> colLid;

    @FXML
    private TableColumn<LessonTM, String> colLtime;

    @FXML
    private TableColumn<LessonTM, String> colRid;

    @FXML
    private TableColumn<LessonTM, String> colStatus;

    @FXML
    private TableColumn<LessonTM, String> colLname;


    LessonBO lessonBO = (LessonBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.LESSON);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        LessonTM lesson = tblLesson.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure ?",
                ButtonType.YES,
                ButtonType.NO
        );

        if (lesson == null) {
            showAlert(Alert.AlertType.WARNING, "Error", "Please select a student first!");
        }

        try {
            boolean isDeleted = lessonBO.delete(lesson.getLessonId());

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

            LessonDTO lesson = new LessonDTO(
                    lblLessonId.getText(),
                    txtLname.getText(),
                    txtDesc.getText(),
                    txtRid.getText(),
                    txtIid.getText(),
                    txtCid.getText(),
                    dateLdate.getValue(),
                    txtLtime.getText(),
                    cmbStatus.getValue()
            );

            boolean isSaved = lessonBO.save(lesson);

            if (isSaved) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Lesson saved successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save lesson. Try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        LessonTM lessonTM = tblLesson.getSelectionModel().getSelectedItem();

        if (lessonTM == null) {
            showAlert(Alert.AlertType.WARNING, "Error", "Please select a student first!");
        }

        try {

            LessonDTO lesson = new LessonDTO(
                    lblLessonId.getText(),
                    txtLname.getText(),
                    txtDesc.getText(),
                    txtRid.getText(),
                    txtIid.getText(),
                    txtCid.getText(),
                    dateLdate.getValue(),
                    txtLtime.getText(),
                    cmbStatus.getValue()
            );

            boolean isUpdated = lessonBO.update(lesson);

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
        tblLesson.getItems().clear();
        ArrayList<LessonDTO> lessonDTOArrayList = (ArrayList<LessonDTO>) lessonBO.getAll();
        ObservableList<LessonTM> lessonTMS = FXCollections.observableArrayList();

        for (LessonDTO lessonDTO : lessonDTOArrayList) {
            LessonTM lessonTM = new LessonTM(
                    lessonDTO.getLessonId(),
                    lessonDTO.getLessonName(),
                    lessonDTO.getLessonDescription(),
                    lessonDTO.getInstructorId(),
                    lessonDTO.getCourseId(),
                    lessonDTO.getLessonDate(),
                    lessonDTO.getLessonTime(),
                    lessonDTO.getStatus()
            );
            lessonTMS.add(lessonTM);
        }
        tblLesson.setItems(lessonTMS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colLid.setCellValueFactory(new PropertyValueFactory<>("lessonId"));
        colLname.setCellValueFactory(new PropertyValueFactory<>("lessonName"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("lessonDescription"));
        colRid.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        colIid.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colCid.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colLdate.setCellValueFactory(new PropertyValueFactory<>("lessonDate"));
        colLtime.setCellValueFactory(new PropertyValueFactory<>("lessonTime"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            loadTableData();
            loadNextId();
            cmbStatus.getItems().addAll("On Going", "Completed","New");

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    private void loadNextId() throws Exception {
        String nextId = lessonBO.getNextId();
        lblLessonId.setText(nextId);
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
            lblLessonId.setText("");
            txtLname.setText("");
            txtDesc.setText("");
            txtIid.setText("");
            txtCid.setText("");
            dateLdate.setValue(null);
            txtLtime.setText("");
            cmbStatus.setValue(null);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        LessonTM selectedRequest = tblLesson.getSelectionModel().getSelectedItem();

        if (selectedRequest != null) {
            lblLessonId.setText(selectedRequest.getLessonId());
            txtLname.setText(selectedRequest.getLessonName());
            txtDesc.setText(selectedRequest.getLessonDescription());
            txtIid.setText(selectedRequest.getInstructorId());
            txtCid.setText(selectedRequest.getCourseId());
            dateLdate.setValue(selectedRequest.getLessonDate());
            txtLtime.setText(selectedRequest.getLessonTime());
            cmbStatus.setValue(selectedRequest.getStatus());

            // save button disable
            btnSave.setDisable(true);

            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
