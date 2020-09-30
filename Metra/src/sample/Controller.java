package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Controller {

    @FXML
    private TextArea scanCode;

    @FXML
    private Button btnScan;

    @FXML
    void initialize() {

        btnScan.setOnAction(event -> {
            String newString;

            newString = scanCode.getText();

            System.out.println(newString);
        });
    }
}
