package cutit.cutit.logic.controller.login;

import cutit.cutit.logic.bean.CustomerBean;
import cutit.cutit.logic.bean.HairdresserBean;
import cutit.cutit.logic.bean.UserBean;
import cutit.cutit.logic.database.dao.CustomerDAO;
import cutit.cutit.logic.database.dao.HairdresserDAO;
import cutit.cutit.logic.database.dao.ShopDAO;
import cutit.cutit.logic.database.dao.UserDAO;
import cutit.cutit.logic.model.Customer;
import cutit.cutit.logic.model.Hairdresser;
import cutit.cutit.logic.model.Shop;
import cutit.cutit.logic.model.User;

public class LoginController {

    public UserBean login(UserBean bean) throws Exception {
        User user = new User(bean.getUsername(), bean.getPasswd(), 3);
        user = UserDAO.getInstance().userLogin(user);
        System.out.println("CONTROLLER APPLICATIVO -> Login (data from CustomerBean passed by my viewController)");
        System.out.println("        Username = " + bean.getUsername() + " Password = " + bean.getPasswd());
        bean.setRole(user.getRole());
        return bean;
    }

    public Boolean signUpCustomer(CustomerBean customerBean) throws Exception {
        Customer customer = new Customer(customerBean.getcEmail(), customerBean.getcPassword(), 0, customerBean.getcName(), customerBean.getcSurname(), customerBean.getcBirthDate(), customerBean.getcGender());
        UserDAO.getInstance().insertNewUser(customer);
        CustomerDAO.getInstance().insertCustomer(customer);
        System.out.println("CONTROLLER APPLICATIVO -> SignUp (data from CustomerBean passed by my viewController)");
        return true;
    }

    public Boolean signUpHair(HairdresserBean hairdresserBean) throws Exception {
        Hairdresser hairdresser = new Hairdresser(hairdresserBean.gethEmail(), hairdresserBean.gethPassword(), 1, hairdresserBean.gethName(), hairdresserBean.gethSurname(), hairdresserBean.getpIVA());
        UserDAO.getInstance().insertNewUser(hairdresser);
        HairdresserDAO.getInstance().insertNewHairdresser(hairdresser);
        Shop shop = new Shop(hairdresserBean.getShopName(), hairdresserBean.getpIVA());
        ShopDAO.insertShop(shop);
        System.out.println("CONTROLLER APPLICATIVO -> SignUp (data from ....... passed by my viewController)");
        return true;
    }

    public HairdresserBean getHairdresser(UserBean userBean) throws Exception {
        User user = new User(userBean.getUsername(), userBean.getPasswd(), userBean.getRole());
        Hairdresser hairdresser = HairdresserDAO.getInstance().getHairdresser(user);
        HairdresserBean hairdresserBean = new HairdresserBean();
        hairdresserBean.sethEmail(hairdresser.getUserID());
        hairdresserBean.sethPassword(hairdresser.getPwd());
        hairdresserBean.setpIVA(hairdresser.getpIVA());
        hairdresserBean.sethName(hairdresser.getName());
        hairdresserBean.sethSurname(hairdresser.getSurname());
        hairdresserBean.setShopName(hairdresser.getShop());
        return hairdresserBean;
    }

    public CustomerBean getCustomer(UserBean userBean) throws Exception {
        User user = new User(userBean.getUsername(), userBean.getPasswd(), userBean.getRole());
        Customer customer = CustomerDAO.getInstance().getCustomer(user);
        CustomerBean customerBean = new CustomerBean();
        customerBean.setcEmail(customer.getUserID());
        customerBean.setcPassword(customer.getPwd());
        customerBean.setcRole(customer.getRole());
        customerBean.setcName(customer.getName());
        customerBean.setcSurname(customer.getSurname());
        customerBean.setcBirthDate(customer.getBirthDate());
        customerBean.setcGender(customer.getGender());
        return customerBean;
    }
}
