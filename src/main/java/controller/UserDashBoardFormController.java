package controller;

import bo.BoFactory;
import bo.custom.UsersBo;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.util.BoType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

public class UserDashBoardFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtEmail;


    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtConfirm;
    private UsersBo usersBo = BoFactory.getInstance().getBo(BoType.USERS);


    public void submitButtonOnAction(javafx.event.ActionEvent actionEvent) {
        String userEmail = txtEmail.getText();
        String userPassword = txtPassword.getText();
        String confirm = txtConfirm.getText();

        if (userEmail.isEmpty() || userPassword.isEmpty() || confirm.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields.").show();
            return;
        }

        if (!userPassword.equals(confirm)) {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match.").show();
            return;
        }

        boolean isUserSaved = usersBo.saveUser(userEmail, userPassword);

        if (isUserSaved) {
            new Alert(Alert.AlertType.INFORMATION, "User registration successful.").show();
            clearFields();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to register user. Please try again.").show();
            clearFields();
        }

    }

    private void clearFields() {
        txtEmail.clear();
        txtPassword.clear();
        txtConfirm.clear();
    }

    public void backButtonOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.setTitle("Dashboard ");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
