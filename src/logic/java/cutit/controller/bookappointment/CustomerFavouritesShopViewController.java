package cutit.controller.bookappointment;

import cutit.bean.firstui.ShopBeanUQ;
import cutit.decorator.ViewLayout;
import cutit.facade.Facade;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CustomerFavouritesShopViewController {

    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
    private ShopBeanUQ shopBeanUQ;

    @FXML
    private VBox vbInScrollCFav;

    public boolean initialize(){
        vbInScrollCFav.setSpacing(15);
        showClientFav();
        System.out.println("CONTROLLER GRAFICO CUSTOMERFAVOURITESSHOPVIEWCONTROLLER");
        return true;
    }

    private void showClientFav() {
        for(int i = 0; i<2; i++){
            Label l = new Label("Barber"+ i);
            l.setPrefSize(895,130);
            l.setMinSize(895,130);
            l.setMaxSize(895,130);
            l.setStyle(labelStyle);
            l.setPadding(new Insets(0,0,10,20));
            l.setOnMouseClicked((MouseEvent) -> Facade.getInstance().decorateView(ViewLayout.SHOPINFO));
            vbInScrollCFav.getChildren().add(l);
        }
    }

    public void fillView(ShopBeanUQ bean){
        shopBeanUQ = bean;
        System.out.println("Filling View from ShopBean data passedBY TopBarCustomerViewController");
        //quì riempirò i campi delle TextFile/TextArea/Label dell'fxml grazie ai getter della bean che mi è stata passata in ingresso
    }

}