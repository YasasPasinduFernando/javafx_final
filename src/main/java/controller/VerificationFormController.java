package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class VerificationFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txt1;

    @FXML
    private JFXTextField txt2;

    @FXML
    private JFXTextField txt3;

    @FXML
    private JFXTextField txt4;

    @FXML
    void backBtnOnAction(ActionEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PasswordForm.fxml"))));
            stage.setTitle(" Password ");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void verifyBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.setTitle(" Dashboard ");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
