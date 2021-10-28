module cutit.cutit {
    requires javafx.controls;
    requires javafx.fxml;


    opens cutit.cutit to javafx.fxml;
    exports cutit.cutit;
    exports cutit.cutit.logic.view.controllerg;
    opens cutit.cutit.logic.view.controllerg to javafx.fxml;
}