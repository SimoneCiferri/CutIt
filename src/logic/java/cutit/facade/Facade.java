package cutit.facade;

import cutit.decorator.ViewComponent1;
import cutit.decorator.ViewLayout1;
import cutit.decorator.concrete_decorator.*;
import cutit.decorator.concrete_view_component.StartView1;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class Facade {

    private static Facade instance;
    private StartView1 startView;
    private static final Map<ViewLayout1, ViewComponent1> viewMap = new EnumMap<>(ViewLayout1.class);
    private static final String ERROR_TITLE = "Error";
    private static final String IO_ERROR_MESSAGE = "Impossible to load some files. If problem persists try again later or contact us at cutitapp@support.com";

    public static synchronized Facade getInstance(){
        if(instance == null){
            instance = new Facade();
        }
        return instance;
    }

    private Facade(){
        initStartView();
    }

    private void initStartView() {
        System.out.println("Singleton Facade created!");
        this.startView = new StartView1();
        try{
            this.startView.loadXML1(ViewLayout1.START);
            decorateView(ViewLayout1.TOPBAR);
            decorateView(ViewLayout1.LOGIN);
        }catch (IOException | NullPointerException e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ERROR_TITLE, IO_ERROR_MESSAGE);
            alert.showAndWait();
            Stage stage = (Stage) getStartView().getPrLayout1().getScene().getWindow();
            stage.close();
        }
    }

    public StartView1 getStartView(){
        return startView;
    }

    public void decorateView(ViewLayout1 layout){
        switch (layout) {
            case HOME -> {
                Home1View homeview = new Home1View(startView);
                viewMap.put(layout, homeview);
            }
            case LOGIN -> {
                LoginView1 loginview = new LoginView1(startView);
                viewMap.put(layout, loginview);
            }
            case TOPBAR -> {
                TopBarView1 topBarView = new TopBarView1(startView);
                viewMap.put(layout, topBarView);
            }

            case TOPBARCUSTOMER -> {
                TopBarCustomerView1 topbarclientview = new TopBarCustomerView1(startView);
                viewMap.put(layout, topbarclientview);
            }
            case TOPBARHAIRDRESSER -> {
                TopBarHairdresserView1 topbarhairdresserview = new TopBarHairdresserView1(startView);
                viewMap.put(layout, topbarhairdresserview);
            }
            case HAIRDRESSERAPPOINTMENTS -> {
                HairdresserAppointmentsView1 hairdresserappointmentview = new HairdresserAppointmentsView1(startView);
                viewMap.put(layout, hairdresserappointmentview);
            }
            case SIGNUP -> {
                SignUpView1 signupview = new SignUpView1(startView);
                viewMap.put(layout, signupview);
            }
            case FAVSHOP -> {
                CustomerFavouritesShopView1 clientfavshopview = new CustomerFavouritesShopView1(startView);
                viewMap.put(layout, clientfavshopview);
            }
            case CUSTOMERPROMOTIONS -> {
                CustomerPromotionsView1 clientpromview = new CustomerPromotionsView1(startView);
                viewMap.put(layout, clientpromview);
            }
            case CUSTOMERAPPOINTMENTS -> {
                CustomerAppointmentsView1 clientappview = new CustomerAppointmentsView1(startView);
                viewMap.put(layout, clientappview);
            }
            case HAIRDRESSERPROMOTIONS -> {
                HairdresserPromotionsView1 hairpromview = new HairdresserPromotionsView1(startView);
                viewMap.put(layout, hairpromview);
            }
            case HAIRDRESSERSERVICES -> {
                HairdresserServicesView1 hairservicesview = new HairdresserServicesView1(startView);
                viewMap.put(layout, hairservicesview);
            }
            case SHOPINFO -> {
                ShopInfoView1 shopinfoview = new ShopInfoView1(startView);
                viewMap.put(layout, shopinfoview);
            }
            case CUSTOMERBOOKAPPOINTMENT -> {
                CustomerBookAppointmentView1 clientbookappview = new CustomerBookAppointmentView1(startView);
                viewMap.put(layout, clientbookappview);
            }
            case CUSTOMERAPPINFO -> {
                CustomerAppointmentInfoView1 clientappinfoview = new CustomerAppointmentInfoView1(startView);
                viewMap.put(layout, clientappinfoview);
            }
            case CUSTOMERPROMOTIONINFO -> {
                CustomerPromotionInfoView1 clientprominfoview = new CustomerPromotionInfoView1(startView);
                viewMap.put(layout, clientprominfoview);
            }
            case GMAPS -> {
                GetLocationDirectionsGoogleMapsView1 customerGetLocationDirectionsView = new GetLocationDirectionsGoogleMapsView1(startView);
                viewMap.put(layout, customerGetLocationDirectionsView);
            }
            case HAIRDRESSERMANAGESHOP -> {
                HairdresserManageShopView1 hairdresserManageShopView = new HairdresserManageShopView1(startView);
                viewMap.put(layout, hairdresserManageShopView);
            }
            case PAYONLINEPAYPAL -> {
                CustomerPayOnlinePayPalView1 customerPayOnlinePayPalView = new CustomerPayOnlinePayPalView1(startView);
                viewMap.put(layout,customerPayOnlinePayPalView);
            }
        }
    }

    public Map<ViewLayout1, ViewComponent1> getViewMap(){
        return viewMap;
    }

    public void logout(){
        startView.getLoaded().clear();
        //forse anche il clear dei controller
    }

    public void forgetMe(ViewLayout1 layout1){
        startView.getLoaded().remove(layout1);
        viewMap.remove(layout1);
    }
}
