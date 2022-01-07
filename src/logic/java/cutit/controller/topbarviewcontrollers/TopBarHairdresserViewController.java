package cutit.controller.topbarviewcontrollers;

import cutit.bean.DeleteAppointmentBean;
import cutit.bean.HairdresserBean;
import cutit.bean.ManageServiceBean;
import cutit.bean.ShopBean;
import cutit.controller.deletebookedappointments.HairdresserDeleteBookedAppointmentsViewController;
import cutit.controller.managepromotions.HairdresserManagePromotionsViewController;
import cutit.controller.manageservices.HairdresserManageServicesViewController;
import cutit.controller.manageshoppage.HairdresserManageShopPageViewController;
import cutit.decorator.ViewLayout;
import cutit.decorator.concreteDecorator.HairdresserAppointmentsView;
import cutit.decorator.concreteDecorator.HairdresserManageShopView;
import cutit.decorator.concreteDecorator.HairdresserPromotionsView;
import cutit.decorator.concreteDecorator.HairdresserServicesView;
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
    private HairdresserBean hairdresserBean;
    private ShopBean shopBean;
    private ManageServiceBean manageServiceBean;
    private DeleteAppointmentBean deleteAppointmentBean;

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
        deleteAppointmentBean = new DeleteAppointmentBean();
        System.out.println("CONTROLLER GRAFICO TOPBARHAIRDRESSERVIEWCONTROLLER");
        return true;
    }

    @FXML
    public boolean goApp() {
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERAPPOINTMENTS);
        btnHApp.setStyle(pageFlagStyle);
        btnHPromotions.setStyle(transparentStyle);
        btnHServices.setStyle(transparentStyle);
        btnHShop.setStyle(transparentStyle);
        btnHLogout.setStyle(transparentStyle);
        deleteAppointmentBean.setShopName(shopBean.getShopName());
        deleteAppointmentBean.setAllAppointments(shopBean.getAllAppointments());
        HairdresserAppointmentsView view = (HairdresserAppointmentsView) Facade.getInstance().getViewMap().get(ViewLayout.HAIRDRESSERAPPOINTMENTS);
        HairdresserDeleteBookedAppointmentsViewController viewController = (HairdresserDeleteBookedAppointmentsViewController) view.getLoadedViewController(ViewLayout.HAIRDRESSERAPPOINTMENTS);
        viewController.fillView(this.deleteAppointmentBean);
        return true;
    }

    @FXML
    public boolean goProm() {
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERPROMOTIONS);
        btnHApp.setStyle(transparentStyle);
        btnHPromotions.setStyle(pageFlagStyle);
        btnHServices.setStyle(transparentStyle);
        btnHShop.setStyle(transparentStyle);
        btnHLogout.setStyle(transparentStyle);
        HairdresserPromotionsView view = (HairdresserPromotionsView) Facade.getInstance().getViewMap().get(ViewLayout.HAIRDRESSERPROMOTIONS);
        HairdresserManagePromotionsViewController viewController = (HairdresserManagePromotionsViewController) view.getLoadedViewController(ViewLayout.HAIRDRESSERPROMOTIONS);
        viewController.fillView(shopBean);
        return true;
    }

    @FXML
    public boolean goServices() {
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERSERVICES);
        btnHApp.setStyle(transparentStyle);
        btnHPromotions.setStyle(transparentStyle);
        btnHServices.setStyle(pageFlagStyle);
        btnHShop.setStyle(transparentStyle);
        btnHLogout.setStyle(transparentStyle);
        HairdresserServicesView view = (HairdresserServicesView)  Facade.getInstance().getViewMap().get(ViewLayout.HAIRDRESSERSERVICES);
        HairdresserManageServicesViewController viewController = (HairdresserManageServicesViewController) view.getLoadedViewController(ViewLayout.HAIRDRESSERSERVICES);
        viewController.fillView(shopBean);
        return true;
    }

    @FXML
    public boolean goShopHair() {
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERMANAGESHOP);
        btnHApp.setStyle(transparentStyle);
        btnHPromotions.setStyle(transparentStyle);
        btnHServices.setStyle(transparentStyle);
        btnHShop.setStyle(pageFlagStyle);
        btnHLogout.setStyle(transparentStyle);
        HairdresserManageShopView view = (HairdresserManageShopView) Facade.getInstance().getViewMap().get(ViewLayout.HAIRDRESSERMANAGESHOP);
        HairdresserManageShopPageViewController viewController = (HairdresserManageShopPageViewController) view.getLoadedViewController(ViewLayout.HAIRDRESSERMANAGESHOP);
        viewController.fillView(shopBean);
        return true;
    }

    @FXML
    public boolean tryLogout() {
        Facade.getInstance().getStartView().getPrLayout().getChildren().remove(apTopBarHairdr);
        Facade.getInstance().logout();
        Facade.getInstance().decorateView(ViewLayout.TOPBAR);
        Facade.getInstance().decorateView(ViewLayout.LOGIN);
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
            AlertFactory.getInstance().generateAlert(Alert.AlertType.ERROR, "", "", "");
        }
    }

    public void startBean(HairdresserBean hairdresserBean, ShopBean shopBean){
        System.out.println("HairdresserBean passedBY LoginViewController");
        this.hairdresserBean = hairdresserBean;
        this.shopBean = shopBean;
        goApp();
    }

}
