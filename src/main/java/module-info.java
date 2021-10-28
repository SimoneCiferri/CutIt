module cutit.cutit {
    requires javafx.controls;
    requires javafx.fxml;


    opens cutit.cutit to javafx.fxml;
    exports cutit.cutit;
}