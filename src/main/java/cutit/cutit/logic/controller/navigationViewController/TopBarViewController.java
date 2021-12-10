package cutit.cutit.logic.controller.navigationViewController;

import cutit.cutit.logic.exception.ExceptionHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TopBarViewController {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private AnchorPane apTopBar;

    @FXML
    private ImageView ivExit, ivReduce;

    public boolean initialize() throws IOException {
        System.out.println("CONTROLLER GRAFICO TOPBARVIEWCONTROLLER");
        setImageView();
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

    private void setImageView() {
        try {
            Image exitI = new Image(Objects.requireNonNull(getClass().getResource("/cutit/cutit/files/exit.png"), "Resource files may be deleted or corrupted. If the problem persist try reinstalling the application.").toString());
            Image comb = new Image(Objects.requireNonNull(getClass().getResource("/cutit/cutit/files/hair_comb.png"), "Resource files may be deleted or corrupted. If the problem persist try reinstalling the application.").toString());
            ivExit.setImage(exitI);
            ivReduce.setImage(comb);
        }catch (NullPointerException e){
            ExceptionHandler.getInstance().handleException(e);
        }
    }

}
