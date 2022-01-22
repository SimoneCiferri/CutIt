package cutit.controller.payonline;

import cutit.bean.AppointmentBean;
import cutit.bean.PayOnlineBean;

import java.util.Random;

public class PayOnlineController {

    public Boolean payAppointment(AppointmentBean appBean){
        return pay();
    }

    private boolean pay() {
        Random random = new Random();
        int upperbound = 100;
        int number = random.nextInt(upperbound);
        System.out.println("Random number is " + number);
        return ((number%2) == 0);
    }



}
