package cutit.factory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


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
        if(diffSpacing){
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

    public HBox createCard(String title, String address, String labelStyle, String imagePath){
        HBox card = new HBox();
        card.setPrefSize(895, 130);
        card.setMinSize(895, 130);
        card.setMaxSize(895, 130);
        card.setStyle(labelStyle);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(0, 0, 10, 20));
        Image im = new Image(Objects.requireNonNull(getClass().getResource(imagePath), "Unable to get resource file " + imagePath).toString());
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
}