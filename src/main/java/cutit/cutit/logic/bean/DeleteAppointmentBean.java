package cutit.cutit.logic.bean;

import cutit.cutit.logic.model.Shop;

import java.time.LocalDateTime;

public class DeleteAppointmentBean {

    private LocalDateTime appointmentDate;
    private Shop appointmentShop;

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }




}
