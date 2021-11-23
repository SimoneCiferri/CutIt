package cutit.cutit.logic.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;

import java.io.IOException;

public class ClientRateShopControllerg {

    private final String star = "/cutit/cutit/files/star.png";
    private Popup popup = ClientShopInfoControllerg.getPop();

    @FXML
    private ImageView ivStar1, ivStar2, ivStar3, ivStar4, ivStar5;

    public boolean initialize() throws IOException {
        System.out.println("Rate Shop page (client)");
        Image image = new Image(getClass().getResource(star).toString());
        ivStar1.setImage(image);
        ivStar2.setImage(image);
        ivStar3.setImage(image);
        ivStar4.setImage(image);
        ivStar5.setImage(image);
        return true;
    }

    @FXML
    public void rate5(){
        System.out.println("5 star pressed");
    }


}
