package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.custom.StudentBO;
import edu.ijse.drivingschool.bo.custom.UserBO;
import edu.ijse.drivingschool.bo.custom.impl.UserBOImpl;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dto.tm.UserTM;
import edu.ijse.drivingschool.entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import edu.ijse.drivingschool.util.PasswordUtil;

import java.io.IOException;

public class LogInController {

    @FXML
    private AnchorPane ancLogin;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblSignUp;

    @FXML
    private PasswordField pwdPassword;

    @FXML
    private TextField txtUsnm;

    UserBO userBO = (UserBO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @FXML
    private TableColumn<UserTM, String> colEmail;

    @FXML
    private TableColumn<UserTM, String> colFn;

    @FXML
    private TableColumn<UserTM, String> colLn;

    @FXML
    private TableColumn<UserTM, Integer> colPhone;

    @FXML
    private TableColumn<UserTM, String> colPwd;

    @FXML
    private TableColumn<UserTM, String> colRole;

    @FXML
    private TableColumn<UserTM, String> colUid;

    @FXML
    private TableColumn<UserTM, String> colUsnm;

    @FXML
    void btnLoginAction(ActionEvent event) {
        String username = txtUsnm.getText().trim();
        String password = pwdPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Username and Password Cannot be empty");
            txtUsnm.requestFocus();
            return;
        }

        User user = userBO.verifyUsername(username);

        if (user == null) {
            showAlert(Alert.AlertType.ERROR, "Authentication Failed", "User not found");
            return;
        }

        if (!PasswordUtil.matches(password, user.getPassword())) {
            showAlert(Alert.AlertType.ERROR, "Authentication Failed", "Wrong password");
            return;
        }

        showAlert(Alert.AlertType.ERROR, "Login Success", "Welcome"+user.getUsername());
        loadDashboard(user);
    }

    @FXML
    void lblSignUpOnMouseClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignUp.fxml"));
        Parent load = loader.load();

        Stage signUpStage = new Stage();
        signUpStage.setTitle("SignUp");
        signUpStage.setScene(new Scene(load));
        signUpStage.initModality(Modality.APPLICATION_MODAL);
        signUpStage.show();

        Stage currentStage = (Stage) lblSignUp.getScene().getWindow();
        currentStage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadDashboard(User user) {
        String fxmlPath;

        switch (user.getRole()) {
            case "admin":
                fxmlPath = "/view/AdminDash.fxml";
                break;
            case "receptionist":
                fxmlPath ="/view/ReceptionistDash.fxml";
                break;
            default:
                showAlert(Alert.AlertType.ERROR, "Access Denied", "Unknown role: " + user.getRole());
                return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("Elite Driving School Dashboard");
            dashboardStage.setScene(new Scene(root));
            dashboardStage.setMaximized(true); // full screen
            dashboardStage.show();

            //Close the login window
            Stage currentStage = (Stage) ancLogin.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load dashboard for role: " + user.getRole());
        }

    }
}
