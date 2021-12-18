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
        /*
        Customer customer = CustomerDAO.getInstance().getCustomer(user);
        CustomerBean customerBean = new CustomerBean();
        customerBean.setEmail(customer.getUserID());
        customerBean.setPassword(customer.getPwd());
        customerBean.setRole(customer.getRole());
        customerBean.setName(customer.getName());
        customerBean.setSurname(customer.getSurname());
        customerBean.setAge(customer.getAge());
        customerBean.setGender(customer.getGender());
        return customerBean;

         */
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
        Shop shop = new Shop(hairdresserBean.getpIVA(), hairdresserBean.getShopName());
        ShopDAO.insertShop(shop);
        System.out.println("CONTROLLER APPLICATIVO -> SignUp (data from ....... passed by my viewController)");
        return true;
    }

    public HairdresserBean getHairdresser(UserBean userBean) throws Exception {
        User user = new User(userBean.getUsername(), userBean.getPasswd(), userBean.getRole());
        Hairdresser hairdresser = HairdresserDAO.getInstance().getHairdresser(user);
        HairdresserBean hairdresserBean = new HairdresserBean();
        hairdresserBean.setEmail(hairdresser.getUserID());
        hairdresserBean.setPassword(hairdresser.getPwd());
        hairdresserBean.setpIVA(hairdresser.getpIVA());
        hairdresserBean.setName(hairdresser.getName());
        hairdresserBean.setSurname(hairdresser.getSurname());
        return hairdresserBean;
    }
}
