module cutit.cutit {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.jfoenix;



    exports cutit.cutit.logic.views;
    opens cutit.cutit.logic.views to javafx.fxml;
    exports cutit.cutit.logic.control;
    opens cutit.cutit.logic.control to javafx.fxml;
    exports cutit.cutit.logic.facade;
    opens cutit.cutit.logic.facade to javafx.fxml;
}