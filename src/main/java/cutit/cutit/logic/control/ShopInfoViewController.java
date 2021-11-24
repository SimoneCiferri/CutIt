package cutit.cutit.logic.control;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import cutit.cutit.logic.views.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class ShopInfoViewController {

    private final Stage prStage = Client.getPrStage();
    private BorderPane pLayout = null;
    private HBox ndLayout = null;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";
    private static Popup pop = null;

    public boolean initialize() throws IOException {
        /*
        Client.setNdLayout(bpInShopInfo);
        System.out.println("Shop page");
        btnPhoto.setStyle(pageFlagStyle);
        btnPriceList.setStyle(transparentStyle);
        btnTime.setStyle(transparentStyle);
        btnHairdresser.setStyle(transparentStyle);

         */
        return true;
    }

    @FXML
    public boolean goPhoto() throws IOException {
        /*
        System.out.println("Photo Button pressed (client)");
        VBox photoLayout = null;
        photoLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/clientshopphoto.fxml"));
        bpInShopInfo.setCenter(photoLayout);
        btnPhoto.setStyle(pageFlagStyle);
        btnPriceList.setStyle(transparentStyle);
        btnTime.setStyle(transparentStyle);
        btnHairdresser.setStyle(transparentStyle);
         */
        return true;
    }

    @FXML
    public boolean goPriceList() throws IOException {
        /*
        System.out.println("Price List Button pressed (client)");
        VBox photoLayout = null;
        photoLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/clientsshoppricelist.fxml"));
        bpInShopInfo.setCenter(photoLayout);
        btnPhoto.setStyle(transparentStyle);
        btnPriceList.setStyle(pageFlagStyle);
        btnTime.setStyle(transparentStyle);
        btnHairdresser.setStyle(transparentStyle);
         */
        return true;
    }
    @FXML
    public boolean goTine() throws IOException {
        /*
        System.out.println("Opening Time Button pressed (client)");
        VBox photoLayout = null;
        photoLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/clientsshopopeningtime.fxml"));
        bpInShopInfo.setCenter(photoLayout);
        btnPhoto.setStyle(transparentStyle);
        btnPriceList.setStyle(transparentStyle);
        btnTime.setStyle(pageFlagStyle);
        btnHairdresser.setStyle(transparentStyle);
         */
        return true;
    }
    @FXML
    public boolean goHairdressers() throws IOException {
        /*
        System.out.println("Hairdressers Button pressed (client)");
        VBox photoLayout = null;
        photoLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/clientsshophairdressers.fxml"));
        bpInShopInfo.setCenter(photoLayout);
        btnPhoto.setStyle(transparentStyle);
        btnPriceList.setStyle(transparentStyle);
        btnTime.setStyle(transparentStyle);
        btnHairdresser.setStyle(pageFlagStyle);
         */
        return true;
    }

    @FXML
    public boolean bookAppointment() throws IOException{
        /*
        System.out.println("Book appointment Button pressed");
        BorderPane bookAppLayout = null;
        bookAppLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/clientbookappointment.fxml"));
        Image image = new Image(getClass().getResource(Client.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        bookAppLayout.setBackground(new Background(back));
        pLayout= Client.getPrLayout();
        pLayout.setCenter(bookAppLayout);
         */
        Facade.getInstance().decorateView(ViewLayout.CLIENTBOOKAPPOINTMENT);
        return true;
    }

    @FXML
    public boolean rateShop() throws IOException{
        /*
        System.out.println("Rate Shop Button pressed");
        VBox rateLayoutPopup = null;
        rateLayoutPopup = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/clientrateshop.fxml"));
        Image image = new Image(getClass().getResource(Client.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        rateLayoutPopup.setBackground(new Background(back));
        setPop(new Popup());
        pop.setAutoHide(true);
        pop.getContent().add(rateLayoutPopup);
        if (!pop.isShowing()){
            pop.show(prStage);
        }
         */
        Facade.getInstance().decorateView(ViewLayout.CLIENTRATESHOP);
        return true;
    }

    public static Popup getPop(){
        return pop;
    }

    private void setPop(Popup popup){
        this.pop = popup;
    }


}
