package controller;

import bo.BoFactory;
import bo.custom.ItemBo;
import bo.custom.UsersBo;
import bo.custom.impl.UsersBoImpl;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.util.BoType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardFormController {
//    UsersBoImpl usersBo = new UsersBoImpl();

    @FXML
    public AnchorPane pane;

    @FXML
    private Label lblDate;

    @FXML
    public Label lblTime;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;
    private UsersBo usersBo = BoFactory.getInstance().getBo(BoType.USERS);


    public void initialize(){
        calculateTime();
        updateDate();
    }

    private void updateDate() {
        lblDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    private void calculateTime() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.ZERO,
                actionEvent -> lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }




    public void submitButtonOnAction(javafx.event.ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        logInSystem();

    }

    private void logInSystem() throws SQLException, ClassNotFoundException {
        String newUserEmail = txtEmail.getText();
        String newUserPassword = txtPassword.getText();

        if (usersBo.doesUserExist(newUserEmail) && usersBo.isUserCredentialsValid(newUserEmail, newUserPassword)) {
            new Alert(Alert.AlertType.INFORMATION, "User login successful.").show();

            clearFields();

            Stage stage = (Stage) pane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"))));
                stage.setTitle("Main ");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid email or password. Please try again.").show();
            clearFields();
        }

    }

    private void clearFields() {
        txtEmail.clear();
        txtPassword.clear();
    }


    public void registerButtonOnAction(javafx.event.ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserDashBoardForm.fxml"))));
            stage.setTitle("User Dashboard ");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void forgotBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PasswordForm.fxml"))));
            stage.setTitle("Password ");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
