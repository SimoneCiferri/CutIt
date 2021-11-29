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
    private static Map<ViewLayout, ViewComponent> viewMap = new EnumMap<>(ViewLayout.class);

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
            case UNLOGGEDPROMOTIONS -> {
                UnloggedPromotionView promotionView = new UnloggedPromotionView(startView);
                viewMap.put(layout, promotionView);
            }
            case LOGIN -> {
                LoginView loginview = new LoginView(startView);
                viewMap.put(layout, loginview);
            }
            case TOPBARCLIENT -> {
                TopBarClientView topbarclientview = new TopBarClientView(startView);
                viewMap.put(layout, topbarclientview);
            }
            case TOPBARHAIRDRESSER -> {
                TopBarHairdresserView topbarhairdresserview = new TopBarHairdresserView(startView);
                viewMap.put(layout, topbarhairdresserview);
            }
            case TOPBAR -> {
                TopBarView topbarview = new TopBarView(startView);
                viewMap.put(layout, topbarview);
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
                ClientFavouritesShopView clientfavshopview = new ClientFavouritesShopView(startView);
                viewMap.put(layout, clientfavshopview);
            }
            case PROMOTIONCLIENT -> {
                ClientPromotionsView clientpromview = new ClientPromotionsView(startView);
                viewMap.put(layout, clientpromview);
            }
            case APPCL -> {
                ClientAppointmentsView clientappview = new ClientAppointmentsView(startView);
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
            case HAIRDRESSERSHOP -> {
                HairdresserShopView hairshopview = new HairdresserShopView(startView);
                viewMap.put(layout, hairshopview);
            }
            case SHOPINFO -> {
                ShopInfoView shopinfoview = new ShopInfoView(startView);
                viewMap.put(layout, shopinfoview);
            }
            case CLIENTRATESHOP -> {
                ClientRateShopView clientrateview = new ClientRateShopView(startView);
                viewMap.put(layout, clientrateview);
            }
            case CLIENTBOOKAPPOINTMENT -> {
                ClientBookAppointmentView clientbookappview = new ClientBookAppointmentView(startView);
                viewMap.put(layout, clientbookappview);
            }
            case CLIENTBOOKAPPFORM -> {
                ClientBookAppointmentFormView clientbookformview = new ClientBookAppointmentFormView(startView);
                viewMap.put(layout, clientbookformview);
            }
            case HAIRDRESSERAPPINFO -> {
                HairdresserAppointmentInfoView hairdresserappinfoview = new HairdresserAppointmentInfoView(startView);
                viewMap.put(layout, hairdresserappinfoview);
            }
            case HAIRDRESSERPROMINFO -> {
                HairdresserPromotionInfoView hairproinfoview = new HairdresserPromotionInfoView(startView);
                viewMap.put(layout, hairproinfoview);
            }
            case CLIENTAPPINFO -> {
                ClientAppointmentInfoView clientappinfoview = new ClientAppointmentInfoView(startView);
                viewMap.put(layout, clientappinfoview);
            }
            case HAIRDRESSERSERVICEINFO -> {
                HairdresserServiceInfoView hairserviceinfoview = new HairdresserServiceInfoView(startView);
                viewMap.put(layout, hairserviceinfoview);
            }
            case CLIENTPROMOTIONINFO -> {
                ClientPromotionInfoView clientprominfoview = new ClientPromotionInfoView(startView);
                viewMap.put(layout, clientprominfoview);
            }
            case HAIRDRESSERADDPROM -> {
                HairdresserAddPromotionView hairaddpromview = new HairdresserAddPromotionView(startView);
                viewMap.put(layout, hairaddpromview);
            }
            case HAIRDRESSERADDSERVICE -> {
                HairdresserAddServiceView hairaddserviceview = new HairdresserAddServiceView(startView);
                viewMap.put(layout, hairaddserviceview);
            }
            default -> throw new IllegalStateException("Illegal state type" + layout);
        }
    }

    public void logout(){
        startView.getLoaded().clear();
        //forse anche il clear dei controller
        decorateView(ViewLayout.TOPBAR);
        decorateView(ViewLayout.HOME);
    }
}
