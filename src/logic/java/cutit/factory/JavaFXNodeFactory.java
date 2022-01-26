package cutit.factory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import java.io.File;
import java.util.List;
import java.util.Objects;

public class JavaFXNodeFactory {

    private static JavaFXNodeFactory instance = null;

    private JavaFXNodeFactory(){}

    public static synchronized JavaFXNodeFactory getInstance(){
        if(JavaFXNodeFactory.instance == null){
            JavaFXNodeFactory.instance = new JavaFXNodeFactory();
        }
        return JavaFXNodeFactory.instance;
    }

    public Label createLabel(String title, Double fontSize){
        Label l = new Label(title);
        l.setFont(Font.font(fontSize));
        l.setTextFill(Color.WHITE);
        return l;
    }

    public Label createCardLabel(String title, String labelStyle){
        Label l = new Label(title);
        l.setPrefSize(895, 130);
        l.setMinSize(895, 130);
        l.setMaxSize(895, 130);
        l.setStyle(labelStyle);
        l.setPadding(new Insets(0, 0, 10, 20));
        return l;
    }

    public Button createButton(String title){
        return new Button(title);
    }

    public HBox createLRForm(List<Label> leftLabelList, List<Node> rightLabelList, Boolean diffSpacing){
        HBox form = new HBox();
        form.setMaxSize(500,250);
        form.setMinSize(500, 100);

        VBox leftVBox = new VBox();
        leftVBox.setMaxSize(250, 250);
        leftVBox.setMinSize(250, 100);
        leftVBox.setAlignment(Pos.TOP_RIGHT);
        if(Boolean.TRUE.equals(diffSpacing)){
            leftVBox.setSpacing(25);
        }else{
            leftVBox.setSpacing(15);
        }
        leftVBox.setPadding(new Insets(0, 10, 0,0));
        for(int i = 0; i<leftLabelList.size();i++){
            Label l = leftLabelList.get(i);
            l.setTextFill(Color.WHITE);
            leftVBox.getChildren().add(l);
        }

        VBox rightVBox = new VBox();
        rightVBox.setMinSize(250,100);
        rightVBox.setMaxSize(250, 250);
        rightVBox.setAlignment(Pos.TOP_LEFT);
        rightVBox.setSpacing(15);
        for(int i = 0; i<rightLabelList.size();i++){
            Node n = rightLabelList.get(i);
            rightVBox.getChildren().add(n);
        }
        form.getChildren().addAll(leftVBox, rightVBox);
        return form;
    }

    public HBox createBottomButtons(Button leftButton, Button rightButton){
        HBox buttonsHB = new HBox();
        buttonsHB.setMaxSize(600,55);
        buttonsHB.setMinSize(600, 55);
        HBox leftButtonHB = new HBox();
        leftButtonHB.setMaxSize(300,55);
        leftButtonHB.setMinSize(300, 55);
        leftButtonHB.setAlignment(Pos.CENTER_LEFT);
        leftButtonHB.getChildren().add(leftButton);
        HBox rightButtonHB = new HBox();
        rightButtonHB.setMaxSize(300,55);
        rightButtonHB.setMinSize(300, 55);
        rightButtonHB.setAlignment(Pos.CENTER_RIGHT);
        rightButtonHB.getChildren().add(rightButton);
        buttonsHB.getChildren().addAll(leftButtonHB, rightButtonHB);
        return buttonsHB;
    }

    public HBox createImageCard(String title, String address, String labelStyle, File file){
        HBox card = getDefaultBox(labelStyle);
        Image im = new Image(String.valueOf(file.toURI()));
        setHBox(title, address, card, im);
        return card;
    }

    public HBox createImageCard(String title, String address, String labelStyle){
        HBox card = getDefaultBox(labelStyle);
        Image im = new Image(Objects.requireNonNull(getClass().getResource("/cutit/cutit/files/blank-profile-picture.png")).toString());
        setHBox(title, address, card, im);
        return card;
    }

    private HBox getDefaultBox(String labelStyle){
        HBox card = new HBox();
        card.setPrefSize(895, 130);
        card.setMinSize(895, 130);
        card.setMaxSize(895, 130);
        card.setStyle(labelStyle);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(0, 0, 10, 20));
        return card;
    }

    private HBox setHBox(String title, String address, HBox card, Image im) {
        ImageView iv = new ImageView();
        iv.setFitHeight(75);
        iv.setFitWidth(75);
        iv.setImage(im);
        card.getChildren().add(iv);
        if(address == null){
            address = "";
        }
        Label l = new Label(title + '\n' + address);
        l.setPrefSize(895, 130);
        l.setMinSize(895, 130);
        l.setMaxSize(895, 130);
        l.setPadding(new Insets(0, 0, 10, 20));
        card.getChildren().add(l);
        return card;
    }

    public VBox createFavouritesShopCard(String shopName, String shopAddress){
        VBox vbShop = new VBox();
        vbShop.setMinSize(400, 200);
        vbShop.setPrefSize(400, 200);
        vbShop.setMaxSize(400, 200);
        String style = "-fx-border-color: black; -fx-border-radius: 5;";
        vbShop.setStyle(style);
        vbShop.setAlignment(Pos.TOP_LEFT);
        Label name = createLabel2(shopName, 30.0);
        Label address = createLabel2(shopAddress, 12.0);
        vbShop.getChildren().addAll(name, address);
        return vbShop;
    }

    public Label createLabel2(String title, Double fontSize){
        Label l = new Label(title);
        l.setTextFill(Color.BLACK);
        l.setFont(Font.font(fontSize));
        return l;
    }

    public Label createCardLabel2(String title, String labelStyle){
        Label l = new Label(title);
        l.setAlignment(Pos.CENTER);
        l.setTextFill(Color.BLACK);
        l.setStyle(labelStyle);
        l.setMinSize(150, 80);
        l.setMaxSize(300, 80);
        l.setStyle(labelStyle);
        l.setPadding(new Insets(0, 0, 10, 20));
        return l;
    }

}
