package cutit.cutit.logic.controller;

import cutit.cutit.logic.bean.*;
import cutit.cutit.logic.controller.applController.BookAppointmentController;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

import java.time.LocalDate;

public class CustomerPayedAndBookedViewController {

    private BookAppointmentController bookAppointmentController;

    @FXML
    public void initialize(){
        bookAppointmentController = new BookAppointmentController();
    }

    @FXML
    public void getDirections(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
    }

    @FXML
    public void rateShop(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        Facade.getInstance().decorateView(ViewLayout.CUSTOMERRATESHOP);
    }

    @FXML
    public void addToFavourites(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        Facade.getInstance().decorateView(ViewLayout.FAVSHOP);
    }

    @FXML
    public void addAppToCalendar(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
    }

}
