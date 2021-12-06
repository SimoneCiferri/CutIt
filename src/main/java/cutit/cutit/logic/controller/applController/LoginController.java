package cutit.cutit.logic.controller.applController;

import cutit.cutit.logic.bean.CustomerBean;
import cutit.cutit.logic.bean.UserBean;

public class LoginController {

    public Boolean login(UserBean bean){
        // la bean deve essere di un utente in generale
        //dovrò passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> Login (data from CustomerBean passed by my viewController)");
        System.out.println("        Username = " + bean.getUsername() + " Password = " + bean.getPswd());
        return true;
    }

    public Boolean signUpCustomer(CustomerBean bean){
        // la bean deve essere di un utente in generale
        //dovrò passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> SignUp (data from CustomerBean passed by my viewController)");
        return true;
    }

    public Boolean signUpHair(){
        // la bean deve essere di un utente in generale
        //dovrò passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> SignUp (data from ....... passed by my viewController)");
        return true;
    }
}
