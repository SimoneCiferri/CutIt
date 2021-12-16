package cutit.cutit.logic.controller.login;

import cutit.cutit.logic.bean.CustomerBean;
import cutit.cutit.logic.bean.HairdresserBean;
import cutit.cutit.logic.bean.UserBean;
import cutit.cutit.logic.database.dao.CustomerDAO;
import cutit.cutit.logic.database.dao.HairdresserDAO;
import cutit.cutit.logic.database.dao.UserDAO;
import cutit.cutit.logic.model.Customer;
import cutit.cutit.logic.model.Hairdresser;
import cutit.cutit.logic.model.User;

public class LoginController {

    public Boolean login(UserBean bean) throws Exception {
        User user = new User(bean.getUsername(), bean.getPasswd(), 3);
        UserDAO.getInstance().userLogin(user);
        System.out.println("CONTROLLER APPLICATIVO -> Login (data from CustomerBean passed by my viewController)");
        System.out.println("        Username = " + bean.getUsername() + " Password = " + bean.getPasswd());
        return true;
    }

    public Boolean signUpCustomer(CustomerBean customerBean) throws Exception {
        Customer customer = new Customer(customerBean.getEmail(), customerBean.getPassword(), 0, customerBean.getName(), customerBean.getSurname(), customerBean.getAge(), customerBean.getGender());
        UserDAO.getInstance().insertNewUser(customer);
        CustomerDAO.getInstance().insertCustomer(customer);
        System.out.println("CONTROLLER APPLICATIVO -> SignUp (data from CustomerBean passed by my viewController)");
        return true;
    }

    public Boolean signUpHair(HairdresserBean hairdresserBean) throws Exception {
        Hairdresser hairdresser = new Hairdresser(hairdresserBean.getEmail(), hairdresserBean.getPassword(), 1, hairdresserBean.getName(), hairdresserBean.getSurname(), hairdresserBean.getpIVA());
        UserDAO.getInstance().insertNewUser(hairdresser);
        HairdresserDAO.getInstance().insertNewHairdresser(hairdresser);
        System.out.println("CONTROLLER APPLICATIVO -> SignUp (data from ....... passed by my viewController)");
        return true;
    }
}
