package cutit.facade;

import cutit.decorator.ViewComponent2;
import cutit.decorator.ViewLayout2;
import cutit.decorator.concrete_decorator2.*;
import cutit.decorator.concrete_view_component.StartView2;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import javafx.scene.control.Alert;

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
            AlertFactory.getInstance().generateAlert(Alert.AlertType.ERROR, "","", "");
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
