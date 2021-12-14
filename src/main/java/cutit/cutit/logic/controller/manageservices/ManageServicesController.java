package cutit.cutit.logic.controller.manageservices;

import cutit.cutit.logic.bean.ManageServiceBean;

public class ManageServicesController {

    public Boolean addService(ManageServiceBean serviceBean){
        //dovrò passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> Adding Service (data from ManageServiceBean passed by my viewController)");
        return true;
    }

    public Boolean deleteService(ManageServiceBean serviceBean){
        //dovrò passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> Deleting Service (data from ManageServiceBean passed by my viewController)");
        return true;
    }

}
