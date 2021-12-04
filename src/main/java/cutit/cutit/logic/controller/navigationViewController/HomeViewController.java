package cutit.cutit.logic.controller.navigationViewController;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;

public class HomeViewController {

    private Integer currPage = 1;
    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";

    @FXML
    private VBox vbInScroll;

    public boolean initialize() throws IOException {
        vbInScroll.setSpacing(15);
        showShops();
        return true;
    }

    private void showShops(){
        vbInScroll.getChildren().clear();
        for(Integer i=0; i<6; i++){
            Label l = new Label("Barber"+ i);
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

    private void shopInfo(){
        vbInScroll.getChildren().clear();
        vbInScroll.setAlignment(Pos.TOP_LEFT);
        Label name = new Label("Shop Name");
        name.setTextFill(Color.WHITE);
        vbInScroll.getChildren().addAll(name);
        //Da completare dopo aver fatto la domanda al prof
    }

}
