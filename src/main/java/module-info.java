module cutit.cutit {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    exports cutit.cutit.logic.view.controllerg;
    opens cutit.cutit.logic.view.controllerg to javafx.fxml;
    exports cutit.cutit.logic.view;
    opens cutit.cutit.logic.view to javafx.fxml;
}