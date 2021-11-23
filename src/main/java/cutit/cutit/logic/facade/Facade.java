package cutit.cutit.logic.facade;

public class Facade {

    private static Facade instance;

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
    }
}
