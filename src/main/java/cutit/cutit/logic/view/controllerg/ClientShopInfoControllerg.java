package cutit.cutit.logic.view.controllerg;

import cutit.cutit.logic.view.MainView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientShopInfoControllerg {

    private final Stage prStage = MainView.getPrStage();
    private BorderPane pLayout = null;
    private HBox ndLayout = null;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";

    @FXML
    private BorderPane bpInShopInfo;

    @FXML
    private Label btnPhoto, btnPriceList, btnTime, btnHairdresser;

    public boolean initialize() throws IOException {
        MainView.setNdLayout(bpInShopInfo);
        System.out.println("Shop page (client)");
        btnPhoto.setStyle(pageFlagStyle);
        btnPriceList.setStyle(transparentStyle);
        btnTime.setStyle(transparentStyle);
        btnHairdresser.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goPhoto() throws IOException {
        System.out.println("Photo Button pressed (client)");
        VBox photoLayout = null;
        photoLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/clientshopphoto.fxml"));
        bpInShopInfo.setCenter(photoLayout);
        btnPhoto.setStyle(pageFlagStyle);
        btnPriceList.setStyle(transparentStyle);
        btnTime.setStyle(transparentStyle);
        btnHairdresser.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goPriceList() throws IOException {
        System.out.println("Price List Button pressed (client)");
        VBox photoLayout = null;
        photoLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/clientsshoppricelist.fxml"));
        bpInShopInfo.setCenter(photoLayout);
        btnPhoto.setStyle(transparentStyle);
        btnPriceList.setStyle(pageFlagStyle);
        btnTime.setStyle(transparentStyle);
        btnHairdresser.setStyle(transparentStyle);
        return true;
    }
    @FXML
    public boolean goTine() throws IOException {
        System.out.println("Opening Time Button pressed (client)");
        VBox photoLayout = null;
        photoLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/clientsshopopeningtime.fxml"));
        bpInShopInfo.setCenter(photoLayout);
        btnPhoto.setStyle(transparentStyle);
        btnPriceList.setStyle(transparentStyle);
        btnTime.setStyle(pageFlagStyle);
        btnHairdresser.setStyle(transparentStyle);
        return true;
    }
    @FXML
    public boolean goHairdressers() throws IOException {
        System.out.println("Hairdressers Button pressed (client)");
        VBox photoLayout = null;
        photoLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/clientsshophairdressers.fxml"));
        bpInShopInfo.setCenter(photoLayout);
        btnPhoto.setStyle(transparentStyle);
        btnPriceList.setStyle(transparentStyle);
        btnTime.setStyle(transparentStyle);
        btnHairdresser.setStyle(pageFlagStyle);
        return true;
    }


}
