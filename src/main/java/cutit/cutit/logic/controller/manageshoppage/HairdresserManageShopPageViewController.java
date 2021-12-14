package cutit.cutit.logic.controller.manageshoppage;

import cutit.cutit.logic.bean.ShopBean;
import javafx.fxml.FXML;

public class HairdresserManageShopPageViewController {

    private ShopBean shopBean;
    private ManageShopPageController manageShopPageController;

    @FXML
    public void initialize(){
        shopBean = new ShopBean();
        manageShopPageController = new ManageShopPageController();
        System.out.println("CONTROLLER GRAFICO HAIRDRESSERMANAGESHOPPAGEVIEWCONTROLLER");
    }

    @FXML
    public void manageShopPage(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        manageShopPageController.updateData(this.shopBean);
    }

    public void fillView(ShopBean bean){
        shopBean = bean;
        System.out.println("Filling View from ShopBean data passedBY TopBarHairdresserViewController");
        //quì riempirò i campi delle TextFile/TextArea/Label dell'fxml grazie ai getter della bean che mi è stata passata in ingresso
    }

}
