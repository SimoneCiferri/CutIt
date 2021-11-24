package cutit.cutit.logic.control;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import cutit.cutit.logic.views.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import com.jfoenix.controls.JFXButton;

public class HomeViewController {

    private final Stage prStage = Client.getPrStage();
    private BorderPane pLayout = null;
    private BorderPane nLayout = null;
    private Integer currPage = 1;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";
    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";

    @FXML
    private VBox vbInScroll;

    @FXML
    private JFXButton btnSearch;

    public boolean initialize() throws IOException {
        vbInScroll.setSpacing(15);
        showShops();
        return true;
    }

    private void showShops(){
        for(Integer i=0; i<6; i++){
            Label l = new Label("Barber"+i.toString());
            l.setPrefSize(895,130);
            l.setMinSize(895,130);
            l.setMaxSize(895,130);
            l.setStyle(labelStyle);
            l.setPadding(new Insets(0,0,10,20));
            l.setOnMouseClicked((MouseEvent) -> {
                try {
                    goShopInfo();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            vbInScroll.getChildren().add(l);
        }
        HBox key = new HBox();
        key.setPrefSize(100, 50);
        key.setMaxSize(100, 50);
        key.setMinSize(100, 50);
        key.setSpacing(30);
        Label prev = new Label("<<");
        prev.setDisable(true);
        prev.setOnMouseClicked((MouseEvent) -> {
            currPage--;
            if(currPage <= 1){
                prev.setDisable(true);
            }else{
                prev.setDisable(false);
            }
        });
        Label next = new Label(">>");
        next.setOnMouseClicked((MouseEvent) -> {
            currPage++;
            if(currPage <= 1){
                prev.setDisable(true);
            }else{
                prev.setDisable(false);
            }
        });
        key.getChildren().addAll(prev,next);
        vbInScroll.getChildren().add(key);
    }

    public boolean goShopInfo() throws IOException {
        Facade.getInstance().decorateView(ViewLayout.SHOPINFO);
        return true;
    }

}
