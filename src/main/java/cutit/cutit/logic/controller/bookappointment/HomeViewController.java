package cutit.cutit.logic.controller.bookappointment;

import cutit.cutit.logic.bean.ShopBean;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import java.io.IOException;

public class HomeViewController {

    private Integer currPage = 1;
    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private ShopBean shopBean;

    @FXML
    private VBox vbInScroll;

    public boolean initialize() throws IOException {
        vbInScroll.setSpacing(15);
        showShops();
        System.out.println("CONTROLLER GRAFICO HOMEVIEWCONTROLLER");
        return true;
    }

    private void showShops(){
        vbInScroll.getChildren().clear();
        for(int i = 0; i<6; i++){
            Label l = new Label("Barber"+ i);
            l.setPrefSize(895,130);
            l.setMinSize(895,130);
            l.setMaxSize(895,130);
            l.setStyle(labelStyle);
            l.setPadding(new Insets(0,0,10,20));
            l.setOnMouseClicked((MouseEvent) -> goShopInfo());
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
            prev.setDisable(currPage <= 1);
        });
        Label next = new Label(">>");
        next.setOnMouseClicked((MouseEvent) -> {
            currPage++;
            prev.setDisable(currPage <= 1);
        });
        key.getChildren().addAll(prev,next);
        vbInScroll.getChildren().add(key);
    }

    public void goShopInfo(){
        Facade.getInstance().decorateView(ViewLayout.SHOPINFO);
    }

    public void fillView(ShopBean bean){
        shopBean = bean;
        System.out.println("Filling View from ShopBean data passedBY TopBarCustomerViewController");
        //quì riempirò i campi delle TextFile/TextArea/Label dell'fxml grazie ai getter della bean che mi è stata passata in ingresso
    }

}
