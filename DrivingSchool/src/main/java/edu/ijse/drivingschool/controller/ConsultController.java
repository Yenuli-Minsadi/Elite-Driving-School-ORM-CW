package edu.ijse.drivingschool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ConsultController {
    @FXML
    private Button btnConsultations;

    @FXML
    private Button btnCoordinators;

    @FXML
    private Pane ancConsultDash;

    public void btnCoordinatorsOnAction(ActionEvent actionEvent) throws IOException {
        ancConsultDash.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/Coordinator.fxml"));

        pane.prefWidthProperty().bind(ancConsultDash.widthProperty());
        pane.prefHeightProperty().bind(ancConsultDash.heightProperty());

        ancConsultDash.getChildren().add(pane);
    }

    public void btnConsultationsOnAction(ActionEvent actionEvent) throws IOException {
        ancConsultDash.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/Consultation.fxml"));

        pane.prefWidthProperty().bind(ancConsultDash.widthProperty());
        pane.prefHeightProperty().bind(ancConsultDash.heightProperty());

        ancConsultDash.getChildren().add(pane);
    }
}
