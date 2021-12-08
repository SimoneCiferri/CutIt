package cutit.cutit.logic.controller.navigationViewController;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class TopBarViewController {

    private final String exit = "/cutit/cutit/files/exit.png";
    private final String reduce = "/cutit/cutit/files/hair_comb.png";

    @FXML
    private AnchorPane apTopBar;

    @FXML
    private ImageView ivExit, ivReduce;

    public boolean initialize() throws IOException {
        System.out.println("CONTROLLER GRAFICO TOPBARVIEWCONTROLLER");
        setImageView();
        return true;
    }

    private void setImageView() {
        Image exitI = new Image(getClass().getResource(exit).toString());
        Image comb = new Image(getClass().getResource(reduce).toString());
        ivExit.setImage(exitI);
        ivReduce.setImage(comb);
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
}
