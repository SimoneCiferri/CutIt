package cutit.controller.topbarviewcontrollers;

import cutit.bean.CustomerBean;
import cutit.controller.bookappointment.CustomerAppointmentsViewController;
import cutit.controller.bookappointment.CustomerFavouritesShopViewController;
import cutit.controller.bookappointment.CustomerPromotionsViewController;
import cutit.controller.bookappointment.HomeViewController;
import cutit.decorator.ViewLayout1;
import cutit.decorator.concrete_decorator.CustomerAppointmentsView1;
import cutit.decorator.concrete_decorator.CustomerFavouritesShopView1;
import cutit.decorator.concrete_decorator.CustomerPromotionsView1;
import cutit.decorator.concrete_decorator.Home1View;
import cutit.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TopBarCustomerViewController {

    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-border-radius: 5; -fx-background-color: #A9A9A9; -fx-text-fill: #FFFFFF;";
    private double xOffset = 0;
    private double yOffset = 0;
    private CustomerBean customerBeanFirstUI;

    @FXML
    private Label btnClHome, btnClFav, btnClApp, btnClPromotion, btnClLogout;

    @FXML
    private AnchorPane apTopBarCustomer;

    public boolean initialize() throws IOException {
        btnClHome.setStyle(pageFlagStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClApp.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
        return true;
    }

    @FXML
    public boolean goHome() {
        btnClHome.setStyle(pageFlagStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClApp.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
        Facade.getInstance().decorateView(ViewLayout1.HOME);
        Home1View view = (Home1View)Facade.getInstance().getViewMap().get(ViewLayout1.HOME);
        HomeViewController viewController = (HomeViewController) view.getLoadedViewController1(ViewLayout1.HOME);
        viewController.fillView(customerBeanFirstUI);
        return true;
    }

    @FXML
    public boolean goFav() {
        btnClHome.setStyle(transparentStyle);
        btnClFav.setStyle(pageFlagStyle);
        btnClApp.setStyle(transparentStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
        Facade.getInstance().decorateView(ViewLayout1.FAVSHOP);
        CustomerFavouritesShopView1 view = (CustomerFavouritesShopView1) Facade.getInstance().getViewMap().get(ViewLayout1.FAVSHOP);
        CustomerFavouritesShopViewController viewController = (CustomerFavouritesShopViewController) view.getLoadedViewController1(ViewLayout1.FAVSHOP);
        viewController.fillView(customerBeanFirstUI);
        return true;
    }

    @FXML
    public boolean goApp() {
        btnClHome.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClApp.setStyle(pageFlagStyle);
        btnClPromotion.setStyle(transparentStyle);
        btnClLogout.setStyle(transparentStyle);
        Facade.getInstance().decorateView(ViewLayout1.CUSTOMERAPPOINTMENTS);
        CustomerAppointmentsView1 view = (CustomerAppointmentsView1) Facade.getInstance().getViewMap().get(ViewLayout1.CUSTOMERAPPOINTMENTS);
        CustomerAppointmentsViewController viewController =(CustomerAppointmentsViewController) view.getLoadedViewController1(ViewLayout1.CUSTOMERAPPOINTMENTS);
        viewController.fillView(customerBeanFirstUI);
        return true;
    }

    @FXML
    public boolean goProm() {
        btnClHome.setStyle(transparentStyle);
        btnClFav.setStyle(transparentStyle);
        btnClApp.setStyle(transparentStyle);
        btnClPromotion.setStyle(pageFlagStyle);
        btnClLogout.setStyle(transparentStyle);
        Facade.getInstance().decorateView(ViewLayout1.CUSTOMERPROMOTIONS);
        CustomerPromotionsView1 view =(CustomerPromotionsView1) Facade.getInstance().getViewMap().get(ViewLayout1.CUSTOMERPROMOTIONS);
        CustomerPromotionsViewController viewController = (CustomerPromotionsViewController) view.getLoadedViewController1(ViewLayout1.CUSTOMERPROMOTIONS);
        viewController.fillView(customerBeanFirstUI);
        return true;
    }

    @FXML
    public boolean tryLogout() {
        Facade.getInstance().getStartView().getPrLayout1().getChildren().remove(apTopBarCustomer);
        Facade.getInstance().logout();
        Facade.getInstance().decorateView(ViewLayout1.TOPBAR);
        Facade.getInstance().decorateView(ViewLayout1.LOGIN);
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

    public void startBean(CustomerBean customerBeanFirstUI){
        this.customerBeanFirstUI = customerBeanFirstUI;
        goHome();
    }

}
