package cutit.cutit.logic.controller.applController;

import cutit.cutit.logic.bean.CustomerBean;

public class LoginController {

    public Boolean login(CustomerBean bean){
        // la bean deve essere di un utente in generale
        //dovrò passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> Login (data from CustomerBean passed by my viewController)");
        return true;
    }

}
