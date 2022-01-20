package cutit.controller.leftbarviewcontrollers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LeftBarHairdresserViewController {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private VBox vbLeftBarHairdresser;

    @FXML
    public void initialize(){
        System.out.println("LeftBarHairdresser viewcontroller");
    }

    @FXML
    public void closeIV(){
        Stage stage = (Stage)vbLeftBarHairdresser.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void reduce(){
        Stage stage = (Stage)vbLeftBarHairdresser.getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    void getOffset(MouseEvent event) {
        xOffset = vbLeftBarHairdresser.getScene().getWindow().getX() - event.getScreenX();
        yOffset = vbLeftBarHairdresser.getScene().getWindow().getY() - event.getScreenY();
    }

    @FXML
    void setOff(MouseEvent event) {
        vbLeftBarHairdresser.getScene().getWindow().setX(event.getScreenX() + xOffset);
        vbLeftBarHairdresser.getScene().getWindow().setY(event.getScreenY() + yOffset);
    }
}
