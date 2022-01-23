package cutit.facade;

import cutit.decorator.ViewComponent2;
import cutit.decorator.ViewLayout2;
import cutit.decorator.concrete_decorator2.*;
import cutit.decorator.concrete_view_component2.StartView2;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.EnumMap;
import java.util.Map;

public class Facade2 {

    private static Facade2 instance;
    private StartView2 startView2;
    private static final Map<ViewLayout2, ViewComponent2> viewMap = new EnumMap<>(ViewLayout2.class);

    public static synchronized Facade2 getInstance(){
        if(instance == null){
            instance = new Facade2();
        }
        return instance;
    }

    private Facade2(){
        initStartView2();
    }

    private void initStartView2() {
        System.out.println("Singleton Facade2 created!");
        this.startView2 = new StartView2();
        try{
            this.startView2.loadXML2(ViewLayout2.START2);
            decorateView2(ViewLayout2.LEFTBAR);
            decorateView2(ViewLayout2.LOGIN2);
        }catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            Alert alert = AlertFactory.getInstance().generateAlert(Alert.AlertType.ERROR, "Error",e.getMessage(), "");
            alert.showAndWait();
            Stage stage = (Stage) getStartView2().getPrLayout2().getScene().getWindow();
            stage.close();
        }
    }

    public StartView2 getStartView2(){
        return startView2;
    }

    public void decorateView2(ViewLayout2 layout){
        switch (layout) {
            case LOGIN2 -> {
                LoginView2 login2View = new LoginView2(startView2);
                viewMap.put(layout, login2View);
            }
            case LEFTBAR -> {
                LeftBarView leftBarView = new LeftBarView(startView2);
                viewMap.put(layout, leftBarView);
            }
            case LEFTBARCUSTOMER -> {
                LeftBarCustomerView leftBarCustomerView = new LeftBarCustomerView(startView2);
                viewMap.put(layout, leftBarCustomerView);
            }
            case LEFTBARHAIRDRESSER -> {
                LeftBarHairdresserView leftBarHairdresserView = new LeftBarHairdresserView(startView2);
                viewMap.put(layout, leftBarHairdresserView);
            }
            case HOME2 -> {
                HomeView2 homeView2 = new HomeView2(startView2);
                viewMap.put(layout, homeView2);
            }
            case CUSTOMERAPPOINTMENTS -> {
                CustomerAppointmentsView2 customerAppointmentsView2 = new CustomerAppointmentsView2(startView2);
                viewMap.put(layout, customerAppointmentsView2);
            }
            case CUSTOMERBOOKAPPOINTMENT -> {
                CustomerBookAppointmentView2 customerBookAppointmentView2 = new CustomerBookAppointmentView2(startView2);
                viewMap.put(layout, customerBookAppointmentView2);
            }
            case CUSTOMERFAVOURITESSHOP -> {
                CustomerFavouritesShopView2 customerFavouritesShopView2 = new CustomerFavouritesShopView2(startView2);
                viewMap.put(layout, customerFavouritesShopView2);
            }
            case CUSTOMERPROMOTIONINFO -> {
                CustomerPromotionInfoView2 customerPromotionInfoView2 = new CustomerPromotionInfoView2(startView2);
                viewMap.put(layout, customerPromotionInfoView2);
            }
            case CUSTOMERPROMOTION -> {
                CustomerPromotionView2 customerPromotionView2 = new CustomerPromotionView2(startView2);
                viewMap.put(layout, customerPromotionView2);
            }
            case HAIRDRESSERDELETEBOOKEDAPPOINTMENTS -> {
                HairdresserDeleteBookedAppointmentsView2 hairdresserDeleteBookedAppointmentsView2 = new HairdresserDeleteBookedAppointmentsView2(startView2);
                viewMap.put(layout, hairdresserDeleteBookedAppointmentsView2);
            }
            case HAIRDRESSERMANAGEPROMOTIONS -> {
                HairdresserManagePromotionsView2 hairdresserManagePromotionsView2 = new HairdresserManagePromotionsView2(startView2);
                viewMap.put(layout, hairdresserManagePromotionsView2);
            }
            case HAIRDRESSERMANAGESERVICES -> {
                HairdresserManageServicesView2 hairdresserManageServicesView2 = new HairdresserManageServicesView2(startView2);
                viewMap.put(layout, hairdresserManageServicesView2);
            }
            case HAIRDRESSERMANAGESHOPPAGE -> {
                HairdresserManageShopPageView2 hairdresserManageShopPageView2 = new HairdresserManageShopPageView2(startView2);
                viewMap.put(layout, hairdresserManageShopPageView2);
            }
            case SHOPINFOVIEW2 -> {
                ShopInfoView2 shopInfoView2 = new ShopInfoView2(startView2);
                viewMap.put(layout, shopInfoView2);
            }

        }
    }

    public Map<ViewLayout2, ViewComponent2> getViewMap(){
        return viewMap;
    }

    public void logout(){
        startView2.getLoaded().clear();
        //forse anche il clear dei controller
    }

}
