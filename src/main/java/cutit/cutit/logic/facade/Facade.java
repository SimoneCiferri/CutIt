package cutit.cutit.logic.facade;

import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.decorator.concreteDecorator.*;
import cutit.cutit.logic.decorator.concreteViewComponent.StartView;

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
            e.printStackTrace();
        }
    }

    public StartView getSTartView(){
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
            case HAIRDRESSERMANAGESHOPPAGE -> {
                HairdresserShopView hairshopview = new HairdresserShopView(startView);
                viewMap.put(layout, hairshopview);
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
            case CLIENTAPPINFO -> {
                ClientAppointmentInfoView clientappinfoview = new ClientAppointmentInfoView(startView);
                viewMap.put(layout, clientappinfoview);
            }
            case CLIENTPROMOTIONINFO -> {
                ClientPromotionInfoView clientprominfoview = new ClientPromotionInfoView(startView);
                viewMap.put(layout, clientprominfoview);
            }
            default -> throw new IllegalStateException("Illegal state type" + layout);
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
