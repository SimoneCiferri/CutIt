package cutit.pepper;

import cutit.bean.interfaces.HairdresserBeanInterface;
import cutit.bean.interfaces.ShopBeanInterface;
import cutit.database.dao.AppointmentDAO;
import cutit.database.dao.PromotionDAO;
import cutit.database.dao.ShopDAO;
import cutit.log.LogWriter;
import cutit.model.Appointment;
import cutit.model.Customer;
import cutit.model.Promotion;
import cutit.model.Shop;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PepperClass extends Thread{

    private static final String BRING_A_FRIEND_PHYSICALLY = "bringAFriendPhysically";
    private HairdresserBeanInterface hairdresserBean;
    private ShopBeanInterface shopBean;
    private String pepperActions = "";

    public PepperClass(HairdresserBeanInterface hairdresserBean, ShopBeanInterface shopBean){
        this.hairdresserBean = hairdresserBean;
        this.shopBean = shopBean;
    }

    public void run(){
        try {
            sleep(3000);
            pepperActions = pepperActions + "------------------------------------------------------------Starting Thread for user " + hairdresserBean.gethEmail() + " owner of the shop " + shopBean.getShopName() + "------------------------------------------------------------" + "\n";
            List<Promotion> allShopPromotions = PromotionDAO.getAllPromotion(hairdresserBean.getShopName());
            if(!allShopPromotions.isEmpty()){
                pepperActions = pepperActions + "Shop " + hairdresserBean.getShopName() + " has " + allShopPromotions.size() + " promotion/s" + "\n";
                Shop shop = ShopDAO.getShopFromName(hairdresserBean.getShopName());
                List<Appointment> allShopAppointments = AppointmentDAO.getAllShopAppointments(shop);
                if(!allShopAppointments.isEmpty()){
                    pepperActions = pepperActions + "Shop has " + allShopAppointments.size() + " appointment/s" + "\n";
                    //quì ho tutti gli appuntamenti del mio negozio (TUTTI)
                    List<Customer> allShopCustomer = new ArrayList<>();
                    for (Appointment allShopAppointment : allShopAppointments) {
                        if(!isAlreadyAdded(allShopAppointment, allShopCustomer)){
                            //System.out.println("Customer " + allShopAppointment.getCustomer().getUserID() + " added");
                            allShopCustomer.add(allShopAppointment.getCustomer());
                        }
                    }
                    pepperActions = pepperActions + "Shop has " + allShopCustomer.size() + " customer/s" + "\n";
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
                        pepperActions = pepperActions + "Customer " + pepperData.getCustomer().getUserID() + " ha " + pepperData.getAllCustomerAppointment().size() + " appointments." + "\n";
                    }
                    workOnPromotions(allShopPromotions, pepperDataList, allShopAppointments);
                } else{
                    pepperActions = pepperActions + "Shop has no Appointments booked = no Customer to send promotion (mandale ad utenti random)" + "\n";
                    //altre cose
                }
            } else{
                pepperActions = pepperActions + "Shop has no promotion/s" + "\n";
            }
            pepperActions = pepperActions + "------------------------------------------------------------Thread ended for user " + hairdresserBean.gethEmail() + " owner of the shop " + shopBean.getShopName() + "------------------------------------------------------------" + "\n";
            LogWriter.getInstance().pepperAction(pepperActions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void workOnPromotions(List<Promotion> allShopPromotions, List<PepperData> pepperDataList, List<Appointment> allShopAppointments) {
        List<String> promoServices = getPromoServices(allShopPromotions);
        pepperActions = pepperActions + "Promo services are: " + promoServices + "\n";
        for(int i=0;i<pepperDataList.size();i++){
            for(int k=0;k<promoServices.size();k++){
                int numberOfAppointments = getNumberOfAppointmentOfServices(pepperDataList.get(i), promoServices.get(k));
                pepperActions = pepperActions + "Customer " + pepperDataList.get(i).getCustomer().getUserID() + " has " + numberOfAppointments + " appointment with service " + promoServices.get(k) + "\n";
                tryAssignPromotion(promoServices.get(k), numberOfAppointments, pepperDataList.get(i), allShopPromotions, allShopAppointments);
            }
        }
    }

    private void tryAssignPromotion(String service, Integer numberOfAppointments, PepperData pepperData, List<Promotion> allShopPromotions, List<Appointment> allShopAppointments) {
        switch (numberOfAppointments) {
            case 0 -> {
                assignZero(pepperData.getCustomer(), service, allShopPromotions);
            }
            case 1 -> {
                assignOne(pepperData.getCustomer(), service, allShopPromotions, allShopAppointments);
            }
            default -> {
                assignTwoOrMore(pepperData, numberOfAppointments, service, allShopPromotions);
            }
        }
    }

    private void assignTwoOrMore(PepperData pepperData, int numberOfAppointments, String service, List<Promotion> allShopPromotions) {
        pepperActions = pepperActions + "Try assigning promotion at " + pepperData.getCustomer().getUserID() + " on " + service + " with " + numberOfAppointments + " appointments booked" + "\n";
        Appointment lastAppointment = getLastAppointment(pepperData, service);
        pepperData.getAllCustomerAppointment().remove(lastAppointment);
        Appointment  penultimateAppointment = getLastAppointment(pepperData, service);
        pepperActions = pepperActions + "Penultimate is " + penultimateAppointment.getStartTime() + ", last is " + lastAppointment.getStartTime() + "\n";
        pepperActions = pepperActions + "Try to calculate avg days between " + penultimateAppointment.getStartTime() + " and " + lastAppointment.getStartTime() + "\n";
        long daysBetween = calculateDaysBetween(penultimateAppointment, lastAppointment);
        pepperActions = pepperActions + "Days between " + penultimateAppointment.getStartTime() + " and " + lastAppointment.getStartTime() + " are " + daysBetween + "\n";
        //retrieve data dell'ultimo appuntamento
        LocalDate nextPromotionDay = lastAppointment.getStartTime().toLocalDate();
        //aggiungo i giorni calcolati in precedenza e ho la nuova data (giorno in cui il customer dovrebbe prenotare il prossimo appuntamento)
        nextPromotionDay = nextPromotionDay.plusDays(daysBetween);
        //rispetto a quella data prendo 3 giorni prima e 3 giorni dopo
        LocalDate leftDate = nextPromotionDay.minusDays(3);
        LocalDate rightDate = nextPromotionDay.plusDays(3);
        //retrieve della promozione da inviare al customer
        Promotion promotion = getMinProm(service, allShopPromotions);
        if(promotion != null){
            //se mi trovo nell'intervallo di +- 3 giorni da quella data e la promozione non sarà scaduta allora mando la promozione al Customer
            pepperActions = pepperActions + "Trying to add " + promotion.getCode() + " at " + pepperData.getCustomer().getUserID() + " (only if i'm between " + leftDate + " and " + rightDate + ", perfect day is " + nextPromotionDay + ")" + "\n";
            if(LocalDate.now().isAfter(leftDate) && LocalDate.now().isBefore(rightDate) && LocalDate.now().isBefore(promotion.getExpireDate())){
                try {
                    PromotionDAO.insertPersonalPromotion(pepperData.getCustomer().getUserID(), promotion.getCode());
                    pepperActions = pepperActions + "Promotion " + promotion.getCode() + " added" + "\n";
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                pepperActions = pepperActions + "Promotion " + promotion.getCode() + " not added, i'm not between " + leftDate + " and " + rightDate + " or promotion is expired" + "\n";
            }
        }
    }

    private long calculateDaysBetween(Appointment penultimateAppointment, Appointment lastAppointment) {
        return ChronoUnit.DAYS.between(penultimateAppointment.getStartTime(), lastAppointment.getStartTime());
    }

    private Appointment getLastAppointment(PepperData pepperData, String service) {
        List<Appointment> customerAppointments = pepperData.getAllCustomerAppointment();
        Appointment last = null;
        for (Appointment customerAppointment : customerAppointments) {
            if (last != null) {
                if (Objects.equals(customerAppointment.getService().getServiceName(), service) && customerAppointment.getStartTime().isAfter(last.getStartTime())) {
                    last = customerAppointment;
                }
            } else {
                last = customerAppointment;
            }
        }
        return last;
    }

    private void assignOne(Customer customer, String service, List<Promotion> allShopPromotions, List<Appointment> allShopAppointments) {
        pepperActions = pepperActions + "Try assigning promotion at " + customer.getUserID() + " on " + service + " with one appointment booked" + "\n";
        Appointment appointment = null;
        //faccio il retrieve di quel preciso appuntamento
        for (Appointment allShopAppointment : allShopAppointments) {
            if (Objects.equals(allShopAppointment.getService().getServiceName(), service) && Objects.equals(allShopAppointment.getCustomer().getUserID(), customer.getUserID())) {
                appointment = allShopAppointment;
                break;
            }
        }
        if(appointment != null){
            //faccio il retrieve della promozione da aggiungere
            Promotion promotion = getMinProm(service, allShopPromotions);
            if(promotion != null){
                pepperActions = pepperActions + "Min promotion on " + service + " is " + promotion.getCode() + "\n";
                //prendo la data dell'appuntamento
                LocalDate appointmentDay = appointment.getStartTime().toLocalDate();
                pepperActions = pepperActions + "Last appointment day is " + appointmentDay.toString() + "\n";
                //ci aggiungo un mese
                appointmentDay = appointmentDay.plusMonths(1);
                //rispetto a quella data prendo 3 giorni prima e 3 giorni dopo
                LocalDate leftDate = appointmentDay.minusDays(3);
                LocalDate rightDate = appointmentDay.plusDays(3);
                //se mi trovo nell'intervallo di +- 3 giorni da quella data e la promozione non sarà scaduta allora mando la promozione al Customer
                pepperActions = pepperActions + "Trying to add " + promotion.getCode() + " at " + customer.getUserID() + " (only if i'm between " + leftDate + " and " + rightDate + ", perfect day is " + appointmentDay + ")" + "\n";
                if(LocalDate.now().isAfter(leftDate) && LocalDate.now().isBefore(rightDate) && LocalDate.now().isBefore(promotion.getExpireDate())){
                    try {
                        PromotionDAO.insertPersonalPromotion(customer.getUserID(), promotion.getCode());
                        pepperActions = pepperActions + "Promotion " + promotion.getCode() + " added" + "\n";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    pepperActions = pepperActions + "Promotion " + promotion.getCode() + " not added, i'm not between " + leftDate + " and " + rightDate + " or promotion is expired" + "\n";
                }
            }
        }
    }

    private void assignZero(Customer customer, String service, List<Promotion> allShopPromotions) {
        pepperActions = pepperActions + "Try assigning promotion at " + customer.getUserID() + " on " + service + " with zero appointments booked" + "\n";
        Promotion promotion = getMinProm(service, allShopPromotions);
        if(promotion!= null){
            pepperActions = pepperActions + "Min promotion on " + service + " is " + promotion.getCode() + "\n";
            try {
                pepperActions = pepperActions + "Trying to add" + promotion.getCode() + " at " + customer.getUserID() + "\n";
                PromotionDAO.insertPersonalPromotion(customer.getUserID(), promotion.getCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Promotion getMinProm(String service, List<Promotion> allShopPromotions) {
        Promotion prom = null;
        int offValue = 100;
        for (Promotion allShopPromotion : allShopPromotions) {
            if (Objects.equals(allShopPromotion.getService().getServiceName(), service)) {
                if (allShopPromotion.getOffValue() < offValue) {
                    prom = allShopPromotion;
                    offValue = prom.getOffValue();
                }
            }
        }
        if(Objects.equals(prom.getCode(), BRING_A_FRIEND_PHYSICALLY)){
            prom = null;
        }
        return prom;
    }

    private int getNumberOfAppointmentOfServices(PepperData pepperData, String service) {
        int number = 0;
        for(int i =0; i<pepperData.getAllCustomerAppointment().size();i++){
            if(Objects.equals(pepperData.getAllCustomerAppointment().get(i).getService().getServiceName(), service)){
                number = number+1;
            }
        }
        return number;
    }

    private List<String> getPromoServices(List<Promotion> allShopPromotions) {
        List<String> promoServices = new ArrayList<>();
        for(Promotion promotion: allShopPromotions){
            String service = promotion.getService().getServiceName();
            if(!promoServices.contains(service)){
                promoServices.add(service);
            }
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
