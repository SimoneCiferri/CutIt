package cutit.cutit.logic.controller;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class HairdresserPromotionsViewController {

    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";

    @FXML
    private VBox vbInScrollHProm;

    public boolean initialize() throws IOException {
        vbInScrollHProm.setSpacing(15);
        showHairProm();
        return true;
    }

    private void showHairProm() {
        Button add = new Button("Add Promotion");
        add.setOnMouseClicked((MouseEvent) -> {
            goAddProm();
        });
        vbInScrollHProm.getChildren().add(add);
        for(Integer i=0; i<4; i++) {
            Label l = new Label("Promotion"+i.toString());
            l.setPrefSize(895, 130);
            l.setMinSize(895, 130);
            l.setMaxSize(895, 130);
            l.setStyle(labelStyle);
            l.setPadding(new Insets(0, 0, 10, 20));
            l.setOnMouseClicked((MouseEvent) -> {
                goPromInfo();
            });
            vbInScrollHProm.getChildren().add(l);
        }
    }

    public boolean goPromInfo(){
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERDELETEPROM);
        return true;
    }

    private void goAddProm() {
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERADDPROM);
    }


}
