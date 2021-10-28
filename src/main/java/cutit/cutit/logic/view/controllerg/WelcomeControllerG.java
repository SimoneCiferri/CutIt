package cutit.cutit.logic.view.controllerg;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WelcomeControllerG {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}