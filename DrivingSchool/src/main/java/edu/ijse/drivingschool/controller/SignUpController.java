package edu.ijse.drivingschool.controller;

import edu.ijse.drivingschool.bo.custom.UserBO;
import edu.ijse.drivingschool.dao.DAOFactory;
import edu.ijse.drivingschool.dto.UserDTO;
import edu.ijse.drivingschool.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.mindrot.jbcrypt.BCrypt;

public class SignUpController {

    @FXML
    private Button btnSignUp;

    @FXML
    private Label lblLogin;

    @FXML
    private PasswordField txtConfirmPwd;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPhone;

    @FXML
    private PasswordField txtPwd;

    @FXML
    private TextField txtUsnm;

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private Label lblUserId;

    UserBO userBO = (UserBO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        String userId = lblUserId.getText();
        String username = txtUsnm.getText().trim();
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String password = txtPwd.getText().trim();
        String confirmPassword = txtConfirmPwd.getText().trim();
        String role = cmbRole.getValue();


        if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
            phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || role.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "SignUp Error", "All fields need to be filled!");
            return;
        }

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Invalid email address!");
            return;
        }

        if (!phone.matches("\\d{10}")) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Phone number must be 10 digits!");
            return;
        }

        if (password.length() < 6) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Password must be at least 6 characters!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Passwords do not match!");
            return;
        }

        try {

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            UserDTO newUser = new UserDTO(userId, username, firstName, lastName, email, phone, hashedPassword, role);

            boolean isSaved = userBO.save(newUser);

            if (isSaved) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "User registered successfully!");
//                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to register user. Try again.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    void lblLoginOnMouseClicked(MouseEvent event) {

    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
