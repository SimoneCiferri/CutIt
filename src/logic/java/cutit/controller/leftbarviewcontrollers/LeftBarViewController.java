package cutit.controller.leftbarviewcontrollers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LeftBarViewController {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private VBox vbLeftBar;

    @FXML
    public void initialize(){
        
    }

    @FXML
    public void closeIV(){
        Stage stage = (Stage)vbLeftBar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void reduce(){
        Stage stage = (Stage)vbLeftBar.getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    void getOffset(MouseEvent event) {
        xOffset = vbLeftBar.getScene().getWindow().getX() - event.getScreenX();
        yOffset = vbLeftBar.getScene().getWindow().getY() - event.getScreenY();
    }

    @FXML
    void setOff(MouseEvent event) {
        vbLeftBar.getScene().getWindow().setX(event.getScreenX() + xOffset);
        vbLeftBar.getScene().getWindow().setY(event.getScreenY() + yOffset);
    }


}
