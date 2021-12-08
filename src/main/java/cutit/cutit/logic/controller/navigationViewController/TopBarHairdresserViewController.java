package cutit.cutit.logic.controller.navigationViewController;

import cutit.cutit.logic.bean.DeleteAppointmentBean;
import cutit.cutit.logic.bean.ManagePromotionBean;
import cutit.cutit.logic.bean.ManageServiceBean;
import cutit.cutit.logic.bean.ShopBean;
import cutit.cutit.logic.controller.viewController.HairdresserDeleteBookedAppointmentsViewController;
import cutit.cutit.logic.controller.viewController.HairdresserManagePromotionsViewController;
import cutit.cutit.logic.controller.viewController.HairdresserManageServicesViewController;
import cutit.cutit.logic.controller.viewController.HairdresserManageShopPageViewController;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.decorator.concreteDecorator.HairdresserAppointmentsView;
import cutit.cutit.logic.decorator.concreteDecorator.HairdresserPromotionsView;
import cutit.cutit.logic.decorator.concreteDecorator.HairdresserServicesView;
import cutit.cutit.logic.decorator.concreteDecorator.HairdresserShopView;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TopBarHairdresserViewController {

    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-border-radius: 5; -fx-background-color: #A9A9A9; -fx-text-fill: #FFFFFF;";
    private DeleteAppointmentBean deleteAppointmentBean;

    @FXML
    private Label  btnHApp, btnHPromotions, btnHServices, btnHShop,  btnHLogout;

    @FXML
    private AnchorPane apTopBarHairdr;

    public boolean initialize() throws IOException {
        btnHApp.setStyle(pageFlagStyle);
        btnHPromotions.setStyle(transparentStyle);
        btnHServices.setStyle(transparentStyle);
        btnHShop.setStyle(transparentStyle);
        btnHLogout.setStyle(transparentStyle);
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
        viewController.fillView(new ManagePromotionBean());
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
        viewController.fillView(new ManageServiceBean());
        return true;
    }


    @FXML
    public boolean goShop() {
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERMANAGESHOPPAGE);
        btnHApp.setStyle(transparentStyle);
        btnHPromotions.setStyle(transparentStyle);
        btnHServices.setStyle(transparentStyle);
        btnHShop.setStyle(pageFlagStyle);
        btnHLogout.setStyle(transparentStyle);
        HairdresserShopView view = (HairdresserShopView) Facade.getInstance().getViewMap().get(ViewLayout.HAIRDRESSERMANAGESHOPPAGE);
        HairdresserManageShopPageViewController viewController = (HairdresserManageShopPageViewController) view.getLoadedViewController(ViewLayout.HAIRDRESSERMANAGESHOPPAGE);
        viewController.fillView(new ShopBean()); //bean creata vuota solo per test
        return true;
    }

    @FXML
    public boolean tryLogout() {
        Facade.getInstance().getSTartView().getPrLayout().getChildren().remove(apTopBarHairdr);
        Facade.getInstance().logout();
        Facade.getInstance().decorateView(ViewLayout.LOGIN);
        return true;
    }

    public void startBean(DeleteAppointmentBean bean){
        System.out.println("Getting DeleteAppointmentBean passedBY LoginViewController");
        this.deleteAppointmentBean = bean;
        goApp();
    }

}
