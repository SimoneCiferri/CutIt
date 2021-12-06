module cutit.cutit {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.jfoenix;



    exports cutit.cutit.logic.decorator.concreteDecorator;
    opens cutit.cutit.logic.decorator.concreteDecorator to javafx.fxml;
    exports cutit.cutit.logic.facade;
    opens cutit.cutit.logic.facade to javafx.fxml;
    exports cutit.cutit.logic.decorator.concreteViewComponent;
    opens cutit.cutit.logic.decorator.concreteViewComponent to javafx.fxml;
    exports cutit.cutit.logic.controller.viewController;
    opens cutit.cutit.logic.controller.viewController  to javafx.fxml;
    exports cutit.cutit.logic.controller.navigationViewController;
    opens cutit.cutit.logic.controller.navigationViewController to javafx.fxml;
}