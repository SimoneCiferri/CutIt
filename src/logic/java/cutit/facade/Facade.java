package cutit.facade;

import cutit.decorator.ViewComponent;
import cutit.decorator.ViewLayout;
import cutit.decorator.concreteDecorator.*;
import cutit.decorator.concreteViewComponent.StartView;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.scene.control.Alert;

import java.util.EnumMap;
import java.util.Map;

public class Facade {

    private static Facade instance;
    private StartView startView;
    private static final Map<ViewLayout, ViewComponent> viewMap = new EnumMap<>(ViewLayout.class);

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
        this.startView = new StartView();
        try{
            this.startView.loadXML(ViewLayout.START);
            decorateView(ViewLayout.TOPBAR);
            decorateView(ViewLayout.LOGIN);
        }catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            AlertFactory.getInstance().generateAlert(Alert.AlertType.ERROR, "","", "");
        }
    }

    public StartView getStartView(){
        return startView;
    }

    public void decorateView(ViewLayout layout){
        switch (layout) {
            case HOME -> {
                HomeView homeview = new HomeView(startView);
                viewMap.put(layout, homeview);
            }
            case LOGIN -> {
                LoginView loginview = new LoginView(startView);
                viewMap.put(layout, loginview);
            }
            case TOPBAR -> {
                TopBarView topBarView = new TopBarView(startView);
                viewMap.put(layout, topBarView);
            }

            case TOPBARCUSTOMER -> {
                TopBarCustomerView topbarclientview = new TopBarCustomerView(startView);
                viewMap.put(layout, topbarclientview);
            }
            case TOPBARHAIRDRESSER -> {
                TopBarHairdresserView topbarhairdresserview = new TopBarHairdresserView(startView);
                viewMap.put(layout, topbarhairdresserview);
            }
            case HAIRDRESSERAPPOINTMENTS -> {
                HairdresserAppointmentsView hairdresserappointmentview = new HairdresserAppointmentsView(startView);
                viewMap.put(layout, hairdresserappointmentview);
            }
            case SIGNUP -> {
                SignUpView signupview = new SignUpView(startView);
                viewMap.put(layout, signupview);
            }
            case FAVSHOP -> {
                CustomerFavouritesShopView clientfavshopview = new CustomerFavouritesShopView(startView);
                viewMap.put(layout, clientfavshopview);
            }
            case CUSTOMERPROMOTIONS -> {
                CustomerPromotionsView clientpromview = new CustomerPromotionsView(startView);
                viewMap.put(layout, clientpromview);
            }
            case CUSTOMERAPPOINTMENTS -> {
                CustomerAppointmentsView clientappview = new CustomerAppointmentsView(startView);
                viewMap.put(layout, clientappview);
            }
            case HAIRDRESSERPROMOTIONS -> {
                HairdresserPromotionsView hairpromview = new HairdresserPromotionsView(startView);
                viewMap.put(layout, hairpromview);
            }
            case HAIRDRESSERSERVICES -> {
                HairdresserServicesView hairservicesview = new HairdresserServicesView(startView);
                viewMap.put(layout, hairservicesview);
            }
            case SHOPINFO -> {
                ShopInfoView shopinfoview = new ShopInfoView(startView);
                viewMap.put(layout, shopinfoview);
            }
            case CUSTOMERRATESHOP -> {
                ClientRateShopView clientrateview = new ClientRateShopView(startView);
                viewMap.put(layout, clientrateview);
            }
            case CLIENTBOOKAPPOINTMENT -> {
                ClientBookAppointmentView clientbookappview = new ClientBookAppointmentView(startView);
                viewMap.put(layout, clientbookappview);
            }
            case CUSTOMERAPPINFO -> {
                CustomerAppointmentInfoView clientappinfoview = new CustomerAppointmentInfoView(startView);
                viewMap.put(layout, clientappinfoview);
            }
            case CUSTOMERPROMOTIONINFO -> {
                CustomerPromotionInfoView clientprominfoview = new CustomerPromotionInfoView(startView);
                viewMap.put(layout, clientprominfoview);
            }
            case GMAPS -> {
                CustomerGetLocationDirectionsView customerGetLocationDirectionsView = new CustomerGetLocationDirectionsView(startView);
                viewMap.put(layout, customerGetLocationDirectionsView);
            }
            case HAIRDRESSERMANAGESHOP -> {
                HairdresserManageShopView hairdresserManageShopView = new HairdresserManageShopView(startView);
                viewMap.put(layout, hairdresserManageShopView);
            }
            case PAYONLINEPAYPAL -> {
                CustomerPayOnlinePayPalView customerPayOnlinePayPalView = new CustomerPayOnlinePayPalView(startView);
                viewMap.put(layout,customerPayOnlinePayPalView);


            }
        }
    }

    public Map<ViewLayout, ViewComponent> getViewMap(){
        return viewMap;
    }

    public void logout(){
        startView.getLoaded().clear();
        //forse anche il clear dei controller
    }
}
