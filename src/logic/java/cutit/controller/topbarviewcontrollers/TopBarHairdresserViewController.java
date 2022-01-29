package cutit.controller.topbarviewcontrollers;

import cutit.bean.interfaces.ShopBeanInterface;
import cutit.controller.deletebookedappointments.HairdresserDeleteBookedAppointmentsViewController;
import cutit.controller.managepromotions.HairdresserManagePromotionsViewController;
import cutit.controller.manageservices.HairdresserManageServicesViewController;
import cutit.controller.manageshoppage.HairdresserManageShopPageViewController;
import cutit.decorator.ViewLayout1;
import cutit.decorator.concrete_decorator.*;
import cutit.decorator.concrete_decorator.HairdresserAppointmentsView1;
import cutit.decorator.concrete_decorator.HairdresserServicesView1;
import cutit.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TopBarHairdresserViewController {

    private double xOffset = 0;
    private double yOffset = 0;
    private ShopBeanInterface shopBeanFirstUI;
    private static final String TRANSPARENT_STYLE = "-fx-background-color: transparent; ";
    private static final String PAGE_FLAG_STYLE = "-fx-border-radius: 5; -fx-background-color: #A9A9A9; -fx-text-fill: #FFFFFF;";

    @FXML
    private Label  btnHApp;

    @FXML
    private Label btnHPromotions;

    @FXML
    private Label btnHServices;

    @FXML
    private Label btnHShop;

    @FXML
    private Label btnHLogout;

    @FXML
    private AnchorPane apTopBarHairdr;


    public boolean initialize() {
        btnHApp.setStyle(PAGE_FLAG_STYLE);
        btnHPromotions.setStyle(TRANSPARENT_STYLE);
        btnHServices.setStyle(TRANSPARENT_STYLE);
        btnHShop.setStyle(TRANSPARENT_STYLE);
        btnHLogout.setStyle(TRANSPARENT_STYLE);
        return true;
    }

    @FXML
    public boolean goApp() {
        Facade.getInstance().decorateView(ViewLayout1.HAIRDRESSERAPPOINTMENTS);
        btnHApp.setStyle(PAGE_FLAG_STYLE);
        btnHPromotions.setStyle(TRANSPARENT_STYLE);
        btnHServices.setStyle(TRANSPARENT_STYLE);
        btnHShop.setStyle(TRANSPARENT_STYLE);
        btnHLogout.setStyle(TRANSPARENT_STYLE);
        HairdresserAppointmentsView1 view = (HairdresserAppointmentsView1) Facade.getInstance().getViewMap().get(ViewLayout1.HAIRDRESSERAPPOINTMENTS);
        HairdresserDeleteBookedAppointmentsViewController viewController = (HairdresserDeleteBookedAppointmentsViewController) view.getLoadedViewController1(ViewLayout1.HAIRDRESSERAPPOINTMENTS);
        viewController.fillView(shopBeanFirstUI);
        return true;
    }

    @FXML
    public boolean goPromotion() {
        Facade.getInstance().decorateView(ViewLayout1.HAIRDRESSERPROMOTIONS);
        btnHApp.setStyle(TRANSPARENT_STYLE);
        btnHPromotions.setStyle(PAGE_FLAG_STYLE);
        btnHServices.setStyle(TRANSPARENT_STYLE);
        btnHShop.setStyle(TRANSPARENT_STYLE);
        btnHLogout.setStyle(TRANSPARENT_STYLE);
        HairdresserPromotionsView1 view = (HairdresserPromotionsView1) Facade.getInstance().getViewMap().get(ViewLayout1.HAIRDRESSERPROMOTIONS);
        HairdresserManagePromotionsViewController viewController = (HairdresserManagePromotionsViewController) view.getLoadedViewController1(ViewLayout1.HAIRDRESSERPROMOTIONS);
        viewController.fillView(shopBeanFirstUI);
        return true;
    }

    @FXML
    public boolean goServices() {
        Facade.getInstance().decorateView(ViewLayout1.HAIRDRESSERSERVICES);
        btnHApp.setStyle(TRANSPARENT_STYLE);
        btnHPromotions.setStyle(TRANSPARENT_STYLE);
        btnHServices.setStyle(PAGE_FLAG_STYLE);
        btnHShop.setStyle(TRANSPARENT_STYLE);
        btnHLogout.setStyle(TRANSPARENT_STYLE);
        HairdresserServicesView1 view = (HairdresserServicesView1)  Facade.getInstance().getViewMap().get(ViewLayout1.HAIRDRESSERSERVICES);
        HairdresserManageServicesViewController viewController = (HairdresserManageServicesViewController) view.getLoadedViewController1(ViewLayout1.HAIRDRESSERSERVICES);
        viewController.fillView(shopBeanFirstUI);
        return true;
    }

    @FXML
    public boolean goShopHair() {
        Facade.getInstance().decorateView(ViewLayout1.HAIRDRESSERMANAGESHOP);
        btnHApp.setStyle(TRANSPARENT_STYLE);
        btnHPromotions.setStyle(TRANSPARENT_STYLE);
        btnHServices.setStyle(TRANSPARENT_STYLE);
        btnHShop.setStyle(PAGE_FLAG_STYLE);
        btnHLogout.setStyle(TRANSPARENT_STYLE);
        HairdresserManageShopView1 view = (HairdresserManageShopView1) Facade.getInstance().getViewMap().get(ViewLayout1.HAIRDRESSERMANAGESHOP);
        HairdresserManageShopPageViewController viewController = (HairdresserManageShopPageViewController) view.getLoadedViewController1(ViewLayout1.HAIRDRESSERMANAGESHOP);
        viewController.fillView(shopBeanFirstUI);
        return true;
    }

    @FXML
    public boolean tryLogout() {
        Facade.getInstance().getStartView().getPrLayout1().getChildren().remove(apTopBarHairdr);
        Facade.getInstance().logout();
        Facade.getInstance().decorateView(ViewLayout1.TOPBAR);
        Facade.getInstance().decorateView(ViewLayout1.LOGIN);
        return true;
    }

    @FXML
    public void closeIV(){
        Stage stage = (Stage)apTopBarHairdr.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void reduce(){
        Stage stage = (Stage)apTopBarHairdr.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void getOffset(MouseEvent event) {
        xOffset = apTopBarHairdr.getScene().getWindow().getX() - event.getScreenX();
        yOffset = apTopBarHairdr.getScene().getWindow().getY() - event.getScreenY();
    }

    @FXML
    void setOff(MouseEvent event) {
        apTopBarHairdr.getScene().getWindow().setX(event.getScreenX() + xOffset);
        apTopBarHairdr.getScene().getWindow().setY(event.getScreenY() + yOffset);
    }

    public void startBean(ShopBeanInterface shopBeanFirstUI){
        this.shopBeanFirstUI = shopBeanFirstUI;
        goApp();
    }

}
