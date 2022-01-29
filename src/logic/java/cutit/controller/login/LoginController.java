package cutit.controller.login;

import cutit.bean.interfaces.CustomerBeanInterface;
import cutit.bean.interfaces.HairdresserBeanInterface;
import cutit.bean.interfaces.ShopBeanInterface;
import cutit.bean.interfaces.UserBeanInterface;
import cutit.pepper.PepperClass;
import cutit.database.dao.CustomerDAO;
import cutit.database.dao.HairdresserDAO;
import cutit.database.dao.ShopDAO;
import cutit.database.dao.UserDAO;
import cutit.exception.*;
import cutit.log.LogWriter;
import cutit.model.*;
import cutit.utils.ListFromModelList;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    public boolean login(UserBeanInterface bean) throws DBConnectionException, SQLException, WrongCredentialsException {
        try{
            User user = new User(bean.getUsername(), bean.getPasswd(), 3);
            UserDAO.userLogin(user);
            bean.setRole(user.getRole());
            return true;
        } catch (DBConnectionException | SQLException e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public boolean signUpCustomer(CustomerBeanInterface customerBean) throws DBConnectionException, SQLException, DuplicatedRecordException, WrongInputDataException {
        try {
            if(!customerBean.getcBirthDate().isAfter(LocalDate.now())){
                Customer customer = new Customer(customerBean.getcEmail(), customerBean.getcPassword(), 0, customerBean.getcName(), customerBean.getcSurname(), customerBean.getcBirthDate(), customerBean.getcGender());
                if(!UserDAO.checkUser(customer.getUserID())){
                    CustomerDAO.insertCustomer(customer);
                    return true;
                }else{
                    return false;
                }
            }else{
                throw new WrongInputDataException("Cannot use selected day as the Birthday!");
            }
        }  catch (DBConnectionException | SQLException e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public void getCustomer(UserBeanInterface userBean, CustomerBeanInterface customerBean) throws DBConnectionException, SQLException, RecordNotFoundException {
        try {
            User user = new User(userBean.getUsername(), userBean.getPasswd(), userBean.getRole());
            Customer customer = CustomerDAO.getCustomer(user);
            customerBean.setcEmail(customer.getUserID());
            customerBean.setcPassword(customer.getPwd());
            customerBean.setcRole(customer.getRole());
            customerBean.setcName(customer.getName());
            customerBean.setcSurname(customer.getSurname());
            customerBean.setcBirthDate(customer.getBirthDate());
            customerBean.setcGender(customer.getGender());
            customerBean.setAllPersonalPromotions(ListFromModelList.getStringListFromPromotions(customer.getPromotions()));
        } catch (DBConnectionException | SQLException e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public Boolean signUpHair(HairdresserBeanInterface hairdresserBean) throws DBConnectionException, SQLException, DuplicatedRecordException {
        try {
            Hairdresser hairdresser = new Hairdresser(hairdresserBean.gethEmail(), hairdresserBean.gethPassword(), 1, hairdresserBean.gethName(), hairdresserBean.gethSurname(), hairdresserBean.getpIVA());
            if (!UserDAO.checkUser(hairdresser.getUserID()) && !ShopDAO.checkShop(hairdresserBean.getShopName()) && !HairdresserDAO.checkPIVA(hairdresser.getpIVA())){
                HairdresserDAO.insertNewHairdresser(hairdresser, hairdresserBean.getShopName());
                return true;
            }
            return false;
        } catch (DBConnectionException | SQLException e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public void getHairdresserAndShop(UserBeanInterface userBean, HairdresserBeanInterface hairdresserBean, ShopBeanInterface shopBean) throws DBConnectionException, SQLException, IOException, RecordNotFoundException {
        try {
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
            shopBean.setShopAddress(hairdresser.getShop().getAddress());
            shopBean.setShopPhoneNumber(hairdresser.getShop().getPhoneNumber());
            shopBean.setShopEmployee(hairdresser.getShop().getEmployee());
            shopBean.setShopDescription(hairdresser.getShop().getDescription());
            shopBean.setShopOpenTime(hairdresser.getShop().getOpenTime());
            shopBean.setShopCloseTime(hairdresser.getShop().getCloseTime());
            shopBean.setShopOpenDays(hairdresser.getShop().getOpenDays());
            shopBean.setPromotions(ListFromModelList.getStringListFromPromotions(hairdresser.getShop().getPromotions()));
            shopBean.setServices(ListFromModelList.getStringListFromServices(hairdresser.getShop().getServices()));
            shopBean.setAllAppointments(stringListFromAppList(hairdresser.getShop().getAllAppointments()));
            shopBean.setImages(hairdresser.getShop().getImages());

            pepperFunction(hairdresserBean, shopBean);

        } catch (DBConnectionException | SQLException | IOException e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    private void pepperFunction(HairdresserBeanInterface hairdresserBean, ShopBeanInterface shopBean)  {
        PepperClass p = new PepperClass(hairdresserBean, shopBean);
        p.start();
    }

    private List<String> stringListFromAppList(List<Appointment> allAppointments) { //da vedere, forse settare la lista di bean non di stringhe
        List<String> appList = new ArrayList<>();
        if(!allAppointments.isEmpty()){
            for (Appointment allAppointment : allAppointments) {
                String p = allAppointment.getStartTime().toString();
                appList.add(p);
            }
        }
        return appList;
    }


}
