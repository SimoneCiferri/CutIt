package cutit.cutit.logic.control;

import cutit.cutit.logic.views.Client;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class StartViewController {

    @FXML
    private BorderPane bpStart;


    public boolean initialize() throws IOException {
        System.out.println("Start page ");
        return true;
    }

}
