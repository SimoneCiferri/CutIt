package cutit.controller.payonline;

import cutit.bean.interfaces.AppointmentBeanInterface;

import java.util.Random;

public class PayOnlineController {

    private static final Random random = new Random();

    public Boolean payAppointment(AppointmentBeanInterface appBean){
        return pay(appBean.getCustomer());
    }

    private boolean pay(String customerEmail) {
        int upperbound = 100;
        int number = random.nextInt(upperbound);
        number = number + customerEmail.length();
        return ((number%2) == 0);
    }



}
