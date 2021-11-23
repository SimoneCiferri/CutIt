package cutit.cutit.logic.facade;

import cutit.cutit.logic.decorator.ViewComponent;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.views.HomeView;
import cutit.cutit.logic.views.LoginView;
import cutit.cutit.logic.views.Start;
import cutit.cutit.logic.views.UnloggedPromotionView;

import java.util.EnumMap;
import java.util.Map;

public class Facade {

    private static Facade instance;
    private Start startView;
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
        this.startView = new Start();
        try{
            this.startView.loadXML(ViewLayout.START);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Start getSTartView(){
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
        }
    }
}
