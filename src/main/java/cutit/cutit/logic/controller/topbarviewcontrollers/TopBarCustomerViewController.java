package cutit.cutit.logic.controller.topbarviewcontrollers;

import cutit.cutit.logic.bean.CustomerBean;
import cutit.cutit.logic.bean.ShopBean;
import cutit.cutit.logic.controller.bookappointment.CustomerFavouritesShopViewController;
import cutit.cutit.logic.controller.bookappointment.CustomerPromotionsViewController;
import cutit.cutit.logic.controller.bookappointment.HomeViewController;
import cutit.cutit.logic.controller.getlocationdirections.CustomerAppointmentsViewController;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.decorator.concreteDecorator.CustomerAppointmentsView;
import cutit.cutit.logic.decorator.concreteDecorator.CustomerFavouritesShopView;
import cutit.cutit.logic.decorator.concreteDecorator.CustomerPromotionsView;
import cutit.cutit.logic.decorator.concreteDecorator.HomeView;
import cutit.cutit.logic.facade.Facade;
import cutit.cutit.logic.factory.AlertFactory;
import cutit.cutit.logic.log.LogWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TopBarCustomerViewController {

    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-border-radius: 5; -fx-background-color: #A9A9A9; -fx-text-fill: #FFFFFF;";
    private double xOffset = 0;
    private double yOffset = 0;
    private CustomerBean customerBean;

    @FXML
    private Label btnClHome, btnClFav, btnClApp, btnClPromotion, btnClLogout;

    @FXML
    private AnchorPane apTopBarCustomer;

    @FXML
    private ImageView ivExit, ivReduce;

    public boolean initialize() throws IOException {
        btnClHome.setStyle(pageFlagStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClApp.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
        setImageView();
        System.out.println("CONTROLLER GRAFICO TOPBARCUSTOMERVIEWCONTROLLER");
        return true;
    }

    @FXML
    public boolean goHome() {
        btnClHome.setStyle(pageFlagStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClApp.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
        Facade.getInstance().decorateView(ViewLayout.HOME);
        HomeView view = (HomeView)Facade.getInstance().getViewMap().get(ViewLayout.HOME);
        HomeViewController viewController = (HomeViewController) view.getLoadedViewController(ViewLayout.HOME);
        viewController.fillView(new ShopBean());
        return true;
    }

    @FXML
    public boolean goFav() {
        btnClHome.setStyle(transparentStyle);
        btnClFav.setStyle(pageFlagStyle);
        btnClApp.setStyle(transparentStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
        Facade.getInstance().decorateView(ViewLayout.FAVSHOP);
        CustomerFavouritesShopView view = (CustomerFavouritesShopView) Facade.getInstance().getViewMap().get(ViewLayout.FAVSHOP);
        CustomerFavouritesShopViewController viewController = (CustomerFavouritesShopViewController) view.getLoadedViewController(ViewLayout.FAVSHOP);
        viewController.fillView(new ShopBean());
        return true;
    }

    @FXML
    public boolean goApp() {
        btnClHome.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClApp.setStyle(pageFlagStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
        Facade.getInstance().decorateView(ViewLayout.CUSTOMERAPPOINTMENTS);
        CustomerAppointmentsView view = (CustomerAppointmentsView) Facade.getInstance().getViewMap().get(ViewLayout.CUSTOMERAPPOINTMENTS);
        CustomerAppointmentsViewController viewController =(CustomerAppointmentsViewController) view.getLoadedViewController(ViewLayout.CUSTOMERAPPOINTMENTS);
        viewController.fillView(new ShopBean());
        return true;
    }

    @FXML
    public boolean goProm() {
        btnClHome.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClApp.setStyle(transparentStyle);
        btnClPromotion.setStyle(pageFlagStyle);
        btnClLogout.setStyle(transparentStyle);
        Facade.getInstance().decorateView(ViewLayout.CUSTOMERPROMOTIONS);
        CustomerPromotionsView view =(CustomerPromotionsView) Facade.getInstance().getViewMap().get(ViewLayout.CUSTOMERPROMOTIONS);
        CustomerPromotionsViewController viewController = (CustomerPromotionsViewController) view.getLoadedViewController(ViewLayout.CUSTOMERPROMOTIONS);
        viewController.fillView(new ShopBean());
        return true;
    }

    @FXML
    public boolean tryLogout() {
        Facade.getInstance().getStartView().getPrLayout().getChildren().remove(apTopBarCustomer);
        Facade.getInstance().logout();
        Facade.getInstance().decorateView(ViewLayout.TOPBAR);
        Facade.getInstance().decorateView(ViewLayout.LOGIN);
        return true;
    }

    @FXML
    public void closeIV(){
        Stage stage = (Stage)apTopBarCustomer.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void reduce(){
        Stage stage = (Stage)apTopBarCustomer.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void getOffset(MouseEvent event) {
        xOffset = apTopBarCustomer.getScene().getWindow().getX() - event.getScreenX();
        yOffset = apTopBarCustomer.getScene().getWindow().getY() - event.getScreenY();
    }

    @FXML
    void setOff(MouseEvent event) {
        apTopBarCustomer.getScene().getWindow().setX(event.getScreenX() + xOffset);
        apTopBarCustomer.getScene().getWindow().setY(event.getScreenY() + yOffset);
    }

    private void setImageView() {
        try {
            Image exitI = new Image(Objects.requireNonNull(getClass().getResource("/cutit/cutit/files/exit.png"), "Unable to get resource file cutit/cutit/files/exit.png.").toString());
            Image comb = new Image(Objects.requireNonNull(getClass().getResource("/cutit/cutit/files/hair_comb.png"), "Unable to get resource file /cutit/cutit/files/hair_comb.png.").toString());
            ivExit.setImage(exitI);
            ivReduce.setImage(comb);
        }catch (NullPointerException e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            AlertFactory.getInstance().generateAlert(Alert.AlertType.ERROR, "", "", "");
        }
    }

    public void startBean(CustomerBean bean){
        System.out.println("Getting CustomerBean passedBY LoginViewController");
        this.customerBean = bean;
        goHome();
    }

}
