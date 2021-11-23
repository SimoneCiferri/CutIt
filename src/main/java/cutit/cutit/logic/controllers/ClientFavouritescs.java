package cutit.cutit.logic.controllers;

import cutit.cutit.logic.views.Client;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ClientFavouritescs {

    private final Stage prStage = Client.getPrStage();
    private BorderPane pLayout = null;
    private BorderPane nLayout = null;
    private Integer currPage = 1;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";
    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";
}
