package cutit.controller.login;

import cutit.bean.HairdresserBean;
import cutit.bean.ShopBean;
import cutit.database.dao.AppointmentDAO;
import cutit.database.dao.PromotionDAO;
import cutit.database.dao.ShopDAO;
import cutit.model.Appointment;
import cutit.model.Customer;
import cutit.model.Promotion;
import cutit.model.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PepperClass extends Thread{

    private HairdresserBean hairdresserBean;
    private ShopBean shopBean;

    public PepperClass(HairdresserBean hairdresserBean, ShopBean shopBean){
        this.hairdresserBean = hairdresserBean;
        this.shopBean = shopBean;
    }

    public void run(){
        //Lista di tutti i miei appuntamenti
        try {
            sleep(3000);
            System.out.println("-----------Thread attivo per " + hairdresserBean.gethEmail() + " -> " + shopBean.getShopName());
            List<Promotion> allShopPromotions = PromotionDAO.getAllPromotion(hairdresserBean.getShopName());
            if(!allShopPromotions.isEmpty()){
                System.out.println("Shop has " + allShopPromotions.size() + " promotion/s");
                Shop shop = ShopDAO.getShopFromName(hairdresserBean.getShopName());
                List<Appointment> allShopAppointments = AppointmentDAO.getAllShopAppointments(shop);
                if(!allShopAppointments.isEmpty()){
                    System.out.println("Shop has " + allShopAppointments.size() + " appointment/s");
                    //quì ho tutti gli appuntamenti del mio negozio (TUTTI)
                    List<Customer> allShopCustomer = new ArrayList<>();
                    for (Appointment allShopAppointment : allShopAppointments) {
                        if(!isAlreadyAdded(allShopAppointment, allShopCustomer)){
                            //System.out.println("Customer " + allShopAppointment.getCustomer().getUserID() + " added");
                            allShopCustomer.add(allShopAppointment.getCustomer());
                        }
                    }
                    System.out.println("Shop has " + allShopCustomer.size() + " customer/s");
                    //quì ho tutti i customer (senza ripetizioni)
                    List<PepperData> pepperDataList = new ArrayList<>();

                    for(int i=0;i<allShopCustomer.size();i++){
                        List<Appointment> customerAppointments = new ArrayList<>();
                        for(int j=0;j<allShopAppointments.size();j++){
                            if(Objects.equals(allShopAppointments.get(j).getCustomer().getUserID(), allShopCustomer.get(i).getUserID())){
                                customerAppointments.add(allShopAppointments.get(j));
                            }
                        }
                        //quì ho la lista di appuntamenti del cliente i-esimo che sto verificando nel for e il cliente i-esimo
                        PepperData p = new PepperData(allShopCustomer.get(i), customerAppointments);
                        pepperDataList.add(p);
                    }

                    //quì ho la lista di PepperData: cioè per ogni cliente ho tutti i suoi appuntamenti in questo shop
                    for (PepperData pepperData : pepperDataList) {
                        System.out.println("Customer " + pepperData.getCustomer().getUserID() + " ha " + pepperData.getAllCustomerAppointment().size() + " appointments.");
                    }
                    workOnPromotions(allShopPromotions, pepperDataList);
                } else{
                    System.out.println("Shop has no Appointments booked = no Customer to send promotion (mandale ad utenti random)");
                    //altre cose
                }
            } else{
                System.out.println("Shop has no promotion/s");
            }
            System.out.println("-----------Thread finito per " + hairdresserBean.gethEmail() + " -> " + shopBean.getShopName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void workOnPromotions(List<Promotion> allShopPromotions, List<PepperData> pepperDataList) {
        List<String> promoServices = getPromoServices(allShopPromotions);

        for(int i=0;i<pepperDataList.size();i++){
            for(int k=0;k<promoServices.size();k++){
                Integer numberOfAppointment = getNumberOfAppointmentOfServices(pepperDataList.get(i), promoServices.get(k));
                tryAssignPrmomotion(promoServices.get(k), numberOfAppointment, pepperDataList.get(i).getCustomer());
            }
        }
    }

    private void tryAssignPrmomotion(String s, Integer numberOfAppointment, Customer customer) {

    }

    private Integer getNumberOfAppointmentOfServices(PepperData pepperData, String service) {
        Integer number = 0;

        return number;
    }

    private List<String> getPromoServices(List<Promotion> allShopPromotions) {
        List<String> promoServices = new ArrayList<>();
        for(Promotion promotion: allShopPromotions){
            String service = promotion.getService().getServiceName();
            promoServices.add(service);
        }
        return promoServices;
    }

    private boolean isAlreadyAdded(Appointment appointmentToCheck, List<Customer> allShopCustomer) {
        for(int i =0; i<allShopCustomer.size();i++){
            if(Objects.equals(allShopCustomer.get(i).getUserID(), appointmentToCheck.getCustomer().getUserID())){
                return true;
            }
        }
        return false;
    }

    private static class PepperData {

        private Customer customer;
        private List<Appointment> allCustomerAppointment;

        public PepperData(Customer customer, List<Appointment> allCustomerAppointment){
            setCustomer(customer);
            setAllCustomerAppointment(allCustomerAppointment);
        }

        public Customer getCustomer() {
            return customer;
        }

        private void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public List<Appointment> getAllCustomerAppointment() {
            return allCustomerAppointment;
        }

        private void setAllCustomerAppointment(List<Appointment> allCustomerAppointment) {
            this.allCustomerAppointment = allCustomerAppointment;
        }
    }
}
