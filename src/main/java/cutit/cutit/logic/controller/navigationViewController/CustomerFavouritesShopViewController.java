package cutit.cutit.logic.controller.navigationViewController;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.io.IOException;

public class CustomerFavouritesShopViewController {

    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";

    @FXML
    private VBox vbInScrollCFav;

    public boolean initialize() throws IOException {
        vbInScrollCFav.setSpacing(15);
        showClientFav();
        return true;
    }

    private void showClientFav() {
        for(Integer i=0; i<2; i++){
            Label l = new Label("Barber"+i.toString());
            l.setPrefSize(895,130);
            l.setMinSize(895,130);
            l.setMaxSize(895,130);
            l.setStyle(labelStyle);
            l.setPadding(new Insets(0,0,10,20));
            l.setOnMouseClicked((MouseEvent) -> {
                Facade.getInstance().decorateView(ViewLayout.SHOPINFO);
            });
            vbInScrollCFav.getChildren().add(l);
        }
    }

}
