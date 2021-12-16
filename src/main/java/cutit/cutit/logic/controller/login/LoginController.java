package cutit.cutit.logic.controller.login;

import cutit.cutit.logic.bean.CustomerBean;
import cutit.cutit.logic.bean.HairdresserBean;
import cutit.cutit.logic.bean.UserBean;
import cutit.cutit.logic.database.dao.UserDAO;
import cutit.cutit.logic.model.Customer;
import cutit.cutit.logic.model.Hairdresser;

public class LoginController {

    public Boolean login(UserBean bean) throws Exception {
        // la bean deve essere di un utente in generale
        //dovrò passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> Login (data from CustomerBean passed by my viewController)");
        System.out.println("        Username = " + bean.getUsername() + " Password = " + bean.getPasswd());
        UserDAO.getInstance().userLogin();
        return true;
    }

    public Boolean signUpCustomer(CustomerBean customerBean) throws Exception {
        Customer customer = new Customer(customerBean.getEmail(), customerBean.getPassword(), 0, customerBean.getName(), customerBean.getSurname(), customerBean.getAge(), customerBean.getGender());
        UserDAO.getInstance().insertNewUser(customer);
        System.out.println("CONTROLLER APPLICATIVO -> SignUp (data from CustomerBean passed by my viewController)");
        return true;
    }

    public Boolean signUpHair(HairdresserBean hairdresserBean) throws Exception {
        Hairdresser hairdresser = new Hairdresser(hairdresserBean.getEmail(), hairdresserBean.getPassword(), 1, hairdresserBean.getName(), hairdresserBean.getSurname(), hairdresserBean.getpIVA());
        UserDAO.getInstance().insertNewUser(hairdresser);
        System.out.println("CONTROLLER APPLICATIVO -> SignUp (data from ....... passed by my viewController)");
        return true;
    }
}
