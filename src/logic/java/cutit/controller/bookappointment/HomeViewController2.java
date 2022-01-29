package cutit.controller.bookappointment;

import cutit.bean.*;
import cutit.bean.interfaces.CustomerBeanInterface;
import cutit.bean.interfaces.ShopBeanInterface;
import cutit.bean.interfaces.ShopListBeanInterface;
import cutit.controller.getlocationdirections.GetLocationDirectionsGoogleMapsViewController2;
import cutit.decorator.decorator2.ViewLayout2;
import cutit.decorator.decorator2.concrete_decorator2.CustomerBookAppointmentView2;
import cutit.decorator.decorator2.concrete_decorator2.GetLocationDirectionsGoogleMapsView2;
import cutit.exception.DBConnectionException;
import cutit.exception.ExceptionText;
import cutit.exception.RecordNotFoundException;
import cutit.facade.Facade2;
import cutit.factory.AlertFactory;
import cutit.factory.JavaFXNodeFactory;
import cutit.utils.MyStringBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class HomeViewController2 {

    private CustomerBeanInterface customerBeanSecondUI;
    private ShopBeanInterface shopBean;
    private ShopListBeanInterface shopListBeanSecondUI;
    private BookAppointmentController bookAppointmentController;
    private List<ImageView> ivList = new ArrayList<>();

    @FXML
    private TextField tfSearchByName;

    @FXML
    private TextField tfSearchByPlace;

    @FXML
    private Button btnMaps;

    @FXML
    private Button btnBookAppointment;

    @FXML
    private VBox vbAllShopsInScroll;

    @FXML
    private Label lblShopNameInScroll;

    @FXML
    private Label lblPhoto;

    @FXML
    private ImageView ivShopPhotoInScroll;

    @FXML
    private Label lblShopDescriptionInScroll;

    @FXML
    private Label lblShopAddressInScroll;

    @FXML
    private Label lblShopPhoneNumberInScroll;

    @FXML
    private Label lblOpenTimeInScroll;

    @FXML
    private Label lblServicesInScroll;

    @FXML
    private Label lblShopEmployeeInScroll;

    @FXML
    private VBox vbPhoto;

    @FXML
    private ImageView ivPhoto1;

    @FXML
    private ImageView ivPhoto2;

    @FXML
    private ImageView ivPhoto3;

    @FXML
    private ImageView ivPhoto4;

    @FXML
    private ImageView ivPhoto5;

    @FXML
    private ImageView ivPhoto6;

    @FXML
    private ImageView ivPhoto7;

    @FXML
    private ImageView ivPhoto8;


    @FXML
    public void initialize(){
        shopBean = new ShopBean();
        shopListBeanSecondUI = new ShopListBean();
        bookAppointmentController = new BookAppointmentController();
        ivList = Arrays.asList(ivPhoto1, ivPhoto2, ivPhoto3, ivPhoto4, ivPhoto5, ivPhoto6, ivPhoto7, ivPhoto8);
    }

    private void showAllShops(){
        vbAllShopsInScroll.getChildren().clear();
        try {
            bookAppointmentController.getShops(shopListBeanSecondUI);
            for(int i=0;i<shopListBeanSecondUI.getShopBeanList().size();i++){
                VBox shop = JavaFXNodeFactory.getInstance().createFavouritesShopCard(shopListBeanSecondUI.getShopBeanList().get(i).getShopName(), shopListBeanSecondUI.getShopBeanList().get(i).getShopAddress());
                int n = i;
                shop.setOnMouseClicked(mouseEvent -> showShopInfo(shopListBeanSecondUI.getShopBeanList().get(n).getShopName()));
                vbAllShopsInScroll.getChildren().add(shop);
            }
            if(!shopListBeanSecondUI.getShopBeanList().isEmpty()){
                showShopInfo(shopListBeanSecondUI.getShopBeanList().get(0).getShopName());
            }
        } catch (DBConnectionException de) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException s){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        } catch (IOException ioe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getIoErrorTitle(), ExceptionText.getIoErrorMessage());
            alert.showAndWait();
        }
    }

    private void showShopInfo(String shopName) {
        try {
            bookAppointmentController.getShop(shopBean, shopName);
            showShop();
        } catch (RecordNotFoundException e) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, ExceptionText.getWarningTitle(), e.getMessage());
            alert.showAndWait();
        } catch (DBConnectionException dbe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getConnectionErrorMessage());
            alert.showAndWait();
        } catch (SQLException sException){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getConnectionErrorTitle(), ExceptionText.getSqlErrorMessage());
            alert.showAndWait();
        } catch (IOException ioe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ExceptionText.getIoErrorTitle(), ExceptionText.getIoErrorMessage());
            alert.showAndWait();
        }
    }

    private void showShop() {
        lblShopNameInScroll.setText(shopBean.getShopName());
        btnBookAppointment.setDisable(false);
        if(!Objects.equals(shopBean.getShopPhoneNumber(), "")){
            lblShopPhoneNumberInScroll.setVisible(true);
            lblShopPhoneNumberInScroll.setText(shopBean.getShopPhoneNumber());
        }else{
            lblShopPhoneNumberInScroll.setVisible(false);
        }
        if(!shopBean.getImages().isEmpty()){
            imageEmpty();
        } else {
            Image defaultLogo = new Image(Objects.requireNonNull(HomeViewController2.class.getResource("/cutit/cutit/files/blank-profile-picture.png"), "Resource files may be deleted or corrupted. If the problem persist try reinstalling the application.").toString());
            ivShopPhotoInScroll.setImage(defaultLogo);
            lblPhoto.setVisible(false);
            vbPhoto.setVisible(false);
        }
        if(Objects.equals(shopBean.getShopAddress(), "")){
            btnMaps.setDisable(true);
            lblShopAddressInScroll.setText("Shop address is not available");
        }else{
            btnMaps.setDisable(false);
            lblShopAddressInScroll.setText(shopBean.getShopAddress());
        }
        if(!Objects.equals(shopBean.getShopDescription(), "")){
            lblShopDescriptionInScroll.setText(shopBean.getShopDescription());
        }else{
            lblShopDescriptionInScroll.setText("Welcome to my Shop!");
        }
        Map<Integer,Boolean> opendays = shopBean.getShopOpenDays();
        String openTimes = MyStringBuilder.getStringFromOpenDaysMap(opendays);
        if(!(openTimes.equals(""))){
            openTimes = openTimes + (shopBean.getShopOpenTime() + " - " + shopBean.getShopCloseTime());
            lblOpenTimeInScroll.setText(openTimes);
        } else {
            lblOpenTimeInScroll.setText("Shop is currently closed.");
        }
        if(!shopBean.getServices().isEmpty()){
            List<String> allServices = shopBean.getServices();
            String services = MyStringBuilder.getStringFromServicesStringList(allServices);
            lblServicesInScroll.setText(services);
        } else {
            lblServicesInScroll.setText("Currently the shop does not offer services ");
        }
        if(!Objects.equals(shopBean.getShopEmployee(), "")){
            lblShopEmployeeInScroll.setVisible(true);
            lblShopEmployeeInScroll.setText(shopBean.getShopEmployee());
        }else {
            lblShopEmployeeInScroll.setVisible(false);
        }
    }

    @FXML
    private void filterShops(){
        if(Objects.equals(tfSearchByName.getText(), "") && Objects.equals(tfSearchByPlace.getText(), "")){
            showAllShops();
        } else {
            vbAllShopsInScroll.getChildren().clear();
            if(!Objects.equals(tfSearchByName.getText(), "")){
                filterByName();
            }else if(!Objects.equals(tfSearchByPlace.getText(), "")){
                filterByPlace();
            }
        }
    }

    private void filterByName() {
        for (int i = 0; i < shopListBeanSecondUI.getShopBeanList().size(); i++) {
            if (shopListBeanSecondUI.getShopBeanList().get(i).getShopName().contains(tfSearchByName.getText())) {
                VBox card = JavaFXNodeFactory.getInstance().createFavouritesShopCard(shopListBeanSecondUI.getShopBeanList().get(i).getShopName(), shopListBeanSecondUI.getShopBeanList().get(i).getShopAddress());
                vbAllShopsInScroll.getChildren().add(card);
            }
        }
    }

    private void filterByPlace(){
        for(int i = 0; i< shopListBeanSecondUI.getShopBeanList().size(); i++){
            try{
                if(shopListBeanSecondUI.getShopBeanList().get(i).getShopAddress().contains(tfSearchByPlace.getText())){
                    VBox card = JavaFXNodeFactory.getInstance().createFavouritesShopCard(shopListBeanSecondUI.getShopBeanList().get(i).getShopName(), shopListBeanSecondUI.getShopBeanList().get(i).getShopAddress());
                    vbAllShopsInScroll.getChildren().add(card);
                }
            } catch (NullPointerException ignored){}
        }
    }

    private void imageEmpty(){
        lblPhoto.setVisible(true);
        vbPhoto.setVisible(true);
        for(int i = 0; i< shopBean.getImages().size(); i++){
            if(i==0){
                ivShopPhotoInScroll.setImage(new javafx.scene.image.Image(String.valueOf(shopBean.getImages().get(i).toURI())));
            }
            ivList.get(i).setImage(new Image(String.valueOf(shopBean.getImages().get(i).toURI())));
        }
    }

    @FXML
    public void bookAppointment(){
        Facade2.getInstance().decorateView2(ViewLayout2.CUSTOMERBOOKAPPOINTMENT);
        CustomerBookAppointmentView2 view = (CustomerBookAppointmentView2) Facade2.getInstance().getViewMap().get(ViewLayout2.CUSTOMERBOOKAPPOINTMENT);
        CustomerBookAppointmentViewController2 viewController = (CustomerBookAppointmentViewController2) view.getLoadedViewController2(ViewLayout2.CUSTOMERBOOKAPPOINTMENT);
        viewController.fillView(customerBeanSecondUI, shopBean);
    }

    @FXML
    private void getDirections(){
        Facade2.getInstance().decorateView2(ViewLayout2.GMAPS);
        GetLocationDirectionsGoogleMapsView2 view = (GetLocationDirectionsGoogleMapsView2) Facade2.getInstance().getViewMap().get(ViewLayout2.GMAPS);
        GetLocationDirectionsGoogleMapsViewController2 viewController = (GetLocationDirectionsGoogleMapsViewController2) view.getLoadedViewController2(ViewLayout2.GMAPS);
        bookAppointmentController.getShopDirections(viewController, shopBean);
    }

    public void fillView(CustomerBeanInterface customerBeanSecondUI){
        this.customerBeanSecondUI = customerBeanSecondUI;
        showAllShops();
    }
}
