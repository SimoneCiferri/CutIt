package cutit.controller.topbarviewcontrollers;

import cutit.bean.*;
import cutit.bean.firstui.DeleteAppointmentBeanFirstUI;
import cutit.controller.deletebookedappointments.HairdresserDeleteBookedAppointmentsViewController;
import cutit.controller.managepromotions.HairdresserManagePromotionsViewController;
import cutit.controller.manageservices.HairdresserManageServicesViewController;
import cutit.controller.manageshoppage.HairdresserManageShopPageViewController;
import cutit.decorator.ViewLayout1;
import cutit.decorator.concrete_decorator.*;
import cutit.decorator.concrete_decorator.HairdresserAppointmentsView1;
import cutit.decorator.concrete_decorator.HairdresserServicesView1;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
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

public class TopBarHairdresserViewController {

    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-border-radius: 5; -fx-background-color: #A9A9A9; -fx-text-fill: #FFFFFF;";
    private double xOffset = 0;
    private double yOffset = 0;
    private HairdresserBean hairdresserBeanFirstUI;
    private ShopBean shopBeanFirstUI;

    @FXML
    private Label  btnHApp, btnHPromotions, btnHServices, btnHShop,  btnHLogout;

    @FXML
    private AnchorPane apTopBarHairdr;

    @FXML
    private ImageView ivExit, ivReduce;

    public boolean initialize() throws IOException {
        btnHApp.setStyle(pageFlagStyle);
        btnHPromotions.setStyle(transparentStyle);
        btnHServices.setStyle(transparentStyle);
        btnHShop.setStyle(transparentStyle);
        btnHLogout.setStyle(transparentStyle);
        setImageView();
        System.out.println("CONTROLLER GRAFICO TOPBARHAIRDRESSERVIEWCONTROLLER");
        return true;
    }

    @FXML
    public boolean goApp() {
        Facade.getInstance().decorateView(ViewLayout1.HAIRDRESSERAPPOINTMENTS);
        btnHApp.setStyle(pageFlagStyle);
        btnHPromotions.setStyle(transparentStyle);
        btnHServices.setStyle(transparentStyle);
        btnHShop.setStyle(transparentStyle);
        btnHLogout.setStyle(transparentStyle);
        HairdresserAppointmentsView1 view = (HairdresserAppointmentsView1) Facade.getInstance().getViewMap().get(ViewLayout1.HAIRDRESSERAPPOINTMENTS);
        HairdresserDeleteBookedAppointmentsViewController viewController = (HairdresserDeleteBookedAppointmentsViewController) view.getLoadedViewController1(ViewLayout1.HAIRDRESSERAPPOINTMENTS);
        viewController.fillView(shopBeanFirstUI);
        return true;
    }

    @FXML
    public boolean goProm() {
        Facade.getInstance().decorateView(ViewLayout1.HAIRDRESSERPROMOTIONS);
        btnHApp.setStyle(transparentStyle);
        btnHPromotions.setStyle(pageFlagStyle);
        btnHServices.setStyle(transparentStyle);
        btnHShop.setStyle(transparentStyle);
        btnHLogout.setStyle(transparentStyle);
        HairdresserPromotionsView1 view = (HairdresserPromotionsView1) Facade.getInstance().getViewMap().get(ViewLayout1.HAIRDRESSERPROMOTIONS);
        HairdresserManagePromotionsViewController viewController = (HairdresserManagePromotionsViewController) view.getLoadedViewController1(ViewLayout1.HAIRDRESSERPROMOTIONS);
        viewController.fillView(shopBeanFirstUI);
        return true;
    }

    @FXML
    public boolean goServices() {
        Facade.getInstance().decorateView(ViewLayout1.HAIRDRESSERSERVICES);
        btnHApp.setStyle(transparentStyle);
        btnHPromotions.setStyle(transparentStyle);
        btnHServices.setStyle(pageFlagStyle);
        btnHShop.setStyle(transparentStyle);
        btnHLogout.setStyle(transparentStyle);
        HairdresserServicesView1 view = (HairdresserServicesView1)  Facade.getInstance().getViewMap().get(ViewLayout1.HAIRDRESSERSERVICES);
        HairdresserManageServicesViewController viewController = (HairdresserManageServicesViewController) view.getLoadedViewController1(ViewLayout1.HAIRDRESSERSERVICES);
        viewController.fillView(shopBeanFirstUI);
        return true;
    }

    @FXML
    public boolean goShopHair() {
        Facade.getInstance().decorateView(ViewLayout1.HAIRDRESSERMANAGESHOP);
        btnHApp.setStyle(transparentStyle);
        btnHPromotions.setStyle(transparentStyle);
        btnHServices.setStyle(transparentStyle);
        btnHShop.setStyle(pageFlagStyle);
        btnHLogout.setStyle(transparentStyle);
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

    private void setImageView() {
        try {
            Image exitI = new Image(Objects.requireNonNull(getClass().getResource("/cutit/cutit/files/exit.png"), "Unable to get resource file cutit/cutit/files/exit.png.").toString());
            Image comb = new Image(Objects.requireNonNull(getClass().getResource("/cutit/cutit/files/hair_comb.png"), "Unable to get resource file /cutit/cutit/files/hair_comb.png.").toString());
            ivExit.setImage(exitI);
            ivReduce.setImage(comb);
        }catch (NullPointerException e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            Alert alert = AlertFactory.getInstance().generateAlert(Alert.AlertType.ERROR, "", "", "");
            alert.showAndWait();
            Stage stage = (Stage) Facade.getInstance().getStartView().getPrLayout1().getScene().getWindow();
            stage.close();
        }
    }

    public void startBean(HairdresserBean hairdresserBeanFirstUI, ShopBean shopBeanFirstUI){
        System.out.println("HairdresserBean passedBY LoginViewController");
        this.hairdresserBeanFirstUI = hairdresserBeanFirstUI;
        this.shopBeanFirstUI = shopBeanFirstUI;
        goApp();
    }

}
