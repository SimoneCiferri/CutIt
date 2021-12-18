package cutit.cutit.logic.factory;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CardFactory {

    private static CardFactory instance = null;

    private CardFactory(){}

    public static synchronized CardFactory getInstance(){
        if(CardFactory.instance == null){
            CardFactory.instance = new CardFactory();
        }
        return CardFactory.instance;
    }

    public Label CreateLabel(String title, String labelStyle){
        Label l = new Label(title);
        l.setPrefSize(895, 130);
        l.setMinSize(895, 130);
        l.setMaxSize(895, 130);
        l.setStyle(labelStyle);
        l.setPadding(new Insets(0, 0, 10, 20));
        return l;
    }
}
