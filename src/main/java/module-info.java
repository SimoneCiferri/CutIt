module cutit.cutit {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.jfoenix;


    exports cutit.cutit.logic.controllers;
    opens cutit.cutit.logic.controllers to javafx.fxml;
    exports cutit.cutit.logic.views;
    opens cutit.cutit.logic.views to javafx.fxml;
}