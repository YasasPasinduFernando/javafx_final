package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EmailController {

    @FXML
    private TextField toTextField;

    @FXML
    private TextField subjectTextField;

    @FXML
    private TextArea messageTextArea;

    @FXML
    private Button sendButton;

    @FXML
    private Label statusLabel;

    public TextField getToTextField() {
        return toTextField;
    }

    public TextField getSubjectTextField() {
        return subjectTextField;
    }

    public TextArea getMessageTextArea() {
        return messageTextArea;
    }

    public Button getSendButton() {
        return sendButton;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }

    @FXML
    private void initialize() {

    }
}

