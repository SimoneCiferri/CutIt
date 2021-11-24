package cutit.cutit.logic.facade;

import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.views.*;

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
            case HOME:
                HomeView homeview = new HomeView(startView);
                viewMap.put(layout, homeview);
                break;
            case UNLOGGEDPROMOTIONS:
                UnloggedPromotionView promotionView = new UnloggedPromotionView(startView);
                viewMap.put(layout, promotionView);
                break;
            case LOGIN:
                LoginView loginview = new LoginView(startView);
                viewMap.put(layout,loginview);
                break;
            case TOPBARCLIENT:
                TopBarClientView topbarclientview = new TopBarClientView(startView);
                viewMap.put(layout, topbarclientview);
                break;
            case TOPBARHAIRDRESSER:
                TopBarHairdresserView topbarhairdresserview = new TopBarHairdresserView(startView);
                viewMap.put(layout, topbarhairdresserview);
                break;
            case TOPBAR:
                TopBarView topbarview = new TopBarView(startView);
                viewMap.put(layout, topbarview);
                break;
            case HAIRDRESSERAPPOINTMENTS:
                HairdresserAppointmentsView hairdresserappointmentview = new HairdresserAppointmentsView(startView);
                viewMap.put(layout, hairdresserappointmentview);
                break;
            case SIGNUP:
                SignUpView signupview = new SignUpView(startView);
                viewMap.put(layout, signupview);
                break;
            case FAVSHOP:
                ClientFavouritesShopView clientfavshopview = new ClientFavouritesShopView(startView);
                viewMap.put(layout, clientfavshopview);
                break;
            case PROMOTIONCLIENT:
                ClientPromotionView clientpromview = new ClientPromotionView(startView);
                viewMap.put(layout, clientpromview);
                break;
            case APPCL:
                ClientAppointmentsView clientappview = new ClientAppointmentsView(startView);
                viewMap.put(layout, clientappview);
                break;
            case HAIRDRESSERPROMOTIONS:
                HairdresserPromotionsView hairpromview = new HairdresserPromotionsView(startView);
                viewMap.put(layout, hairpromview);
                break;
            case HAIRDRESSERSERVICES:
                HairdresserServicesView hairservicesview = new HairdresserServicesView(startView);
                viewMap.put(layout, hairservicesview);
                break;
            case HAIRDRESSERSHOP:
                HairdresserShopView hairshopview = new HairdresserShopView(startView);
                viewMap.put(layout, hairshopview);
                break;
            default:
                throw new IllegalStateException("Illegal state type" + layout);
        }
    }

    public void logout(){
        startView.getLoaded().clear();
        decorateView(ViewLayout.HOME);
        decorateView(ViewLayout.TOPBAR);
    }
}
