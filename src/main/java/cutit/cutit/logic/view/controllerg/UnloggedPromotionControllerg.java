package cutit.cutit.logic.view.controllerg;

import cutit.cutit.logic.view.Client;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class UnloggedPromotionControllerg {

    private final Stage prStage = Client.getPrStage();
    private BorderPane pLayout = null;
    private BorderPane nLayout = null;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";
    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";

    @FXML
    private VBox vbUnloggedPromotion;

    public boolean initialize() throws IOException {
        vbUnloggedPromotion.setSpacing(15);
        showPromotions();
        return true;
    }

    private void showPromotions() {
        for(Integer i=0; i<5; i++){
            Label l = new Label("Promotion"+i.toString());
            l.setPrefSize(300,100);
            l.setMinSize(300,100);
            l.setMaxSize(300,100);
            l.setStyle(labelStyle);
            l.setPadding(new Insets(0,0,10,20));
            l.setOnMouseClicked((MouseEvent) -> {
                goPromotionInfo();
            });
            vbUnloggedPromotion.getChildren().add(l);
        }
    }

    private void goPromotionInfo() {
        System.out.println("Promotion info");
    }

}
