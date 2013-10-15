package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

public class FXMLExampleController {
    @FXML public Text actiontarget;

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }
}
