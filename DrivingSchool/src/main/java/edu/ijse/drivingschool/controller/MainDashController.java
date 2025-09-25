package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.BOFactory;
import edu.ijse.drivingschool.bo.custom.QueryBO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainDashController implements Initializable {

    @FXML
    private Pane ancMainDash;

    @FXML
    private Label lblEnrollAllCourses;

    QueryBO queryBO = (QueryBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.QUERY);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStudentsAllCourseEnroll();
    }

    private void  loadStudentsAllCourseEnroll() {
        try {
            int count = queryBO.getStudentsRegisteredForAllCourses();
            lblEnrollAllCourses.setText(String.valueOf(count));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
