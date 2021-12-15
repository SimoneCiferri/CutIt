module cutit.cutit {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.jfoenix;
    requires java.datatransfer;
    requires java.desktop;
    requires java.sql;


    exports cutit.cutit.logic.facade;
    opens cutit.cutit.logic.facade to javafx.fxml;
    exports cutit.cutit.logic.controller.topbarviewcontrollers;
    opens cutit.cutit.logic.controller.topbarviewcontrollers to javafx.fxml;
    exports cutit.cutit.logic.decorator;
    opens cutit.cutit.logic.decorator to javafx.fxml;
    exports cutit.cutit.logic.decorator.concreteViewComponent;
    opens cutit.cutit.logic.decorator.concreteViewComponent to javafx.fxml;
    exports cutit.cutit.logic.bean;
    opens cutit.cutit.logic.bean to javafx.fxml;
    exports cutit.cutit.logic.controller.bookappointment;
    opens cutit.cutit.logic.controller.bookappointment to javafx.fxml;
    exports cutit.cutit.logic.controller.getlocationdirections;
    opens cutit.cutit.logic.controller.getlocationdirections to javafx.fxml;
    exports cutit.cutit.logic.controller.managepromotions;
    opens cutit.cutit.logic.controller.managepromotions to javafx.fxml;
    exports cutit.cutit.logic.controller.manageshoppage;
    opens cutit.cutit.logic.controller.manageshoppage to javafx.fxml;
    exports cutit.cutit.logic.controller.deletebookedappointments;
    opens cutit.cutit.logic.controller.deletebookedappointments to javafx.fxml;
    exports cutit.cutit.logic.controller.manageservices;
    opens cutit.cutit.logic.controller.manageservices to javafx.fxml;
    exports cutit.cutit.logic.controller.addappointmenttocalendar;
    opens cutit.cutit.logic.controller.addappointmenttocalendar to javafx.fxml;
    exports cutit.cutit.logic.controller.facebooklogin;
    opens cutit.cutit.logic.controller.facebooklogin to javafx.fxml;
    exports cutit.cutit.logic.controller.googlelogin;
    opens cutit.cutit.logic.controller.googlelogin to javafx.fxml;
    exports cutit.cutit.logic.controller.payonline;
    opens cutit.cutit.logic.controller.payonline to javafx.fxml;
    exports cutit.cutit.logic.controller.login;
    opens cutit.cutit.logic.controller.login to javafx.fxml;
    exports cutit.cutit.logic.controller.rateshop;
    opens cutit.cutit.logic.controller.rateshop to javafx.fxml;

    /*
    exports cutit.cutit.logic.decorator.concreteDecorator;
    opens cutit.cutit.logic.decorator.concreteDecorator to javafx.fxml;
     */
}