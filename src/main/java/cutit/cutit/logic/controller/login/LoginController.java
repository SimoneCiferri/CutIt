package cutit.cutit.logic.controller.login;

import cutit.cutit.logic.bean.CustomerBean;
import cutit.cutit.logic.bean.HairdresserBean;
import cutit.cutit.logic.bean.ShopBean;
import cutit.cutit.logic.bean.UserBean;
import cutit.cutit.logic.database.dao.CustomerDAO;
import cutit.cutit.logic.database.dao.HairdresserDAO;
import cutit.cutit.logic.database.dao.UserDAO;
import cutit.cutit.logic.model.*;

import java.util.ArrayList;
import java.util.List;

public class LoginController {

    public UserBean login(UserBean bean) throws Exception {
        User user = new User(bean.getUsername(), bean.getPasswd(), 3);
        UserDAO.userLogin(user);
        System.out.println("CONTROLLER APPLICATIVO -> Login (data from CustomerBean passed by my viewController)");
        System.out.println("        Username = " + bean.getUsername() + " Password = " + bean.getPasswd());
        bean.setRole(user.getRole());
        return bean;
    }

    public Boolean signUpCustomer(CustomerBean customerBean) throws Exception {
        Customer customer = new Customer(customerBean.getcEmail(), customerBean.getcPassword(), 0, customerBean.getcName(), customerBean.getcSurname(), customerBean.getcBirthDate(), customerBean.getcGender());
        CustomerDAO.insertCustomer(customer);
        System.out.println("CONTROLLER APPLICATIVO -> SignUp (data from CustomerBean passed by my viewController)");
        return true;
    }

    public Boolean signUpHair(HairdresserBean hairdresserBean) throws Exception {
        Hairdresser hairdresser = new Hairdresser(hairdresserBean.gethEmail(), hairdresserBean.gethPassword(), 1, hairdresserBean.gethName(), hairdresserBean.gethSurname(), hairdresserBean.getpIVA());
        HairdresserDAO.insertNewHairdresser(hairdresser, hairdresserBean.getShopName());
        System.out.println("CONTROLLER APPLICATIVO -> SignUp (data from ....... passed by my viewController)");
        return true;
    }

    public void getHairdresserAndShop(UserBean userBean, HairdresserBean hairdresserBean, ShopBean shopBean) throws Exception {
        User user = new User(userBean.getUsername(), userBean.getPasswd(), userBean.getRole());
        Hairdresser hairdresser = HairdresserDAO.getHairdresser(user);
        hairdresserBean.sethEmail(hairdresser.getUserID());
        hairdresserBean.sethPassword(hairdresser.getPwd());
        hairdresserBean.sethRole(hairdresser.getRole());
        hairdresserBean.sethName(hairdresser.getName());
        hairdresserBean.sethSurname(hairdresser.getSurname());
        hairdresserBean.setpIVA(hairdresser.getpIVA());
        hairdresserBean.setShopName(hairdresser.getShop().getShopName());
        shopBean.setShopName(hairdresser.getShop().getShopName());
        shopBean.setShopPIVA(hairdresser.getShop().getpIVA());
        shopBean.setLatitude(hairdresser.getShop().getLatitude());
        shopBean.setLongitude(hairdresser.getShop().getLongitude());
        shopBean.setPhoneNumber(hairdresser.getShop().getPhoneNumber());
        shopBean.setEmployee(hairdresser.getShop().getEmployee());
        shopBean.setShopDescription(hairdresser.getShop().getDescription());
        shopBean.setOpenTime(hairdresser.getShop().getOpenTime());
        shopBean.setCloseTime(hairdresser.getShop().getCloseTime());
        shopBean.setOpenDays(hairdresser.getShop().getOpenDays());
        shopBean.setPromotions(stringListFromPromList(hairdresser.getShop().getPromotions()));
        shopBean.setServices(stringListFromServList(hairdresser.getShop().getServices()));
        shopBean.setAllAppointments(stringListFromAppList(hairdresser.getShop().getAllAppointments()));
    }

    private List<String> stringListFromAppList(List<Appointment> allAppointments) {
        List<String> appList = new ArrayList<>();
        if(!allAppointments.isEmpty()){
            for(int i = 0; i<allAppointments.size(); i++){
                String p = allAppointments.get(i).getStartTime().toString();
                appList.add(p);
            }
        }
        return appList;
    }

    private List<String> stringListFromServList(List<Service> services) {
        List<String> servList = new ArrayList<>();
        if(!services.isEmpty()){
            for(int i = 0; i<services.size(); i++){
                String p = services.get(i).getServiceName();
                servList.add(p);
            }
        }
        return servList;
    }

    private List<String> stringListFromPromList(List<Promotion> promotions) {
        List<String> promList = new ArrayList<>();
        if(!promotions.isEmpty()){
            for(int i = 0; i<promotions.size(); i++){
                String p = promotions.get(i).getCode();
                promList.add(p);
            }
        }
        return promList;
    }

    public CustomerBean getCustomer(UserBean userBean) throws Exception {
        User user = new User(userBean.getUsername(), userBean.getPasswd(), userBean.getRole());
        Customer customer = CustomerDAO.getCustomer(user);
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
