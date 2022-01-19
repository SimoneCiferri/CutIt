module cutit.cutit {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.jfoenix;
    requires java.datatransfer;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.java;
    requires javafx.web;
    requires com.dlsc.gmapsfx;


    exports cutit.facade;
    opens cutit.facade to javafx.fxml;
    exports cutit.controller.topbarviewcontrollers;
    opens cutit.controller.topbarviewcontrollers to javafx.fxml;
    exports cutit.decorator;
    opens cutit.decorator to javafx.fxml;
    exports cutit.decorator.concrete_view_component;
    opens cutit.decorator.concrete_view_component to javafx.fxml;
    exports cutit.bean;
    opens cutit.bean to javafx.fxml;
    exports cutit.controller.bookappointment;
    opens cutit.controller.bookappointment to javafx.fxml;
    exports cutit.controller.getlocationdirections;
    opens cutit.controller.getlocationdirections to javafx.fxml;
    exports cutit.controller.managepromotions;
    opens cutit.controller.managepromotions to javafx.fxml;
    exports cutit.controller.manageshoppage;
    opens cutit.controller.manageshoppage to javafx.fxml;
    exports cutit.controller.deletebookedappointments;
    opens cutit.controller.deletebookedappointments to javafx.fxml;
    exports cutit.controller.manageservices;
    opens cutit.controller.manageservices to javafx.fxml;
    exports cutit.controller.addappointmenttocalendar;
    opens cutit.controller.addappointmenttocalendar to javafx.fxml;
    exports cutit.controller.facebooklogin;
    opens cutit.controller.facebooklogin to javafx.fxml;
    exports cutit.controller.googlelogin;
    opens cutit.controller.googlelogin to javafx.fxml;
    exports cutit.controller.payonline;
    opens cutit.controller.payonline to javafx.fxml;
    exports cutit.controller.login;
    opens cutit.controller.login to javafx.fxml;
    exports cutit.controller.rateshop;
    opens cutit.controller.rateshop to javafx.fxml;
    exports cutit.bean.firstui;
    opens cutit.bean.firstui to javafx.fxml;
    exports cutit.controller.pepper;
    opens cutit.controller.pepper to javafx.fxml;

    /*
    exports cutit.cutit.logic.decorator.concreteDecorator;
    opens cutit.cutit.logic.decorator.concreteDecorator to javafx.fxml;
     */
}