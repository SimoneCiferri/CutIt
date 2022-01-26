package cutit.controller.topbarviewcontrollers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TopBarViewController {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private AnchorPane apTopBar;


    public boolean initialize() {
        return true;
    }

    @FXML
    public void closeIV(){
        Stage stage = (Stage)apTopBar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void reduce(){
        Stage stage = (Stage)apTopBar.getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    void getOffset(MouseEvent event) {
        xOffset = apTopBar.getScene().getWindow().getX() - event.getScreenX();
        yOffset = apTopBar.getScene().getWindow().getY() - event.getScreenY();
    }

    @FXML
    void setOff(MouseEvent event) {
        apTopBar.getScene().getWindow().setX(event.getScreenX() + xOffset);
        apTopBar.getScene().getWindow().setY(event.getScreenY() + yOffset);
    }

}
