package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PasswordFormController {

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private AnchorPane pane;


    public void backBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.setTitle(" Dashboard ");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void OTPBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/VerificationForm.fxml"))));
            stage.setTitle(" Verification ");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
