package cutit.cutit.logic.controller.manageshoppage;

import cutit.cutit.logic.bean.ShopBean;
import cutit.cutit.logic.bean.UserBean;
import cutit.cutit.logic.database.dao.ServiceDAO;
import cutit.cutit.logic.database.dao.ShopDAO;
import cutit.cutit.logic.database.dao.UserDAO;
import cutit.cutit.logic.model.Service;
import cutit.cutit.logic.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

public class HairdresserManageShopPageViewController {

    private ShopBean shopBean;
    private User user;
    private UserBean userBean;
    private ManageShopPageController manageShopPageController;

    @FXML
    public void initialize(){
        shopBean = new ShopBean();
        manageShopPageController = new ManageShopPageController();
        System.out.println("CONTROLLER GRAFICO HAIRDRESSERMANAGESHOPPAGEVIEWCONTROLLER");
    }

    //fare il retrieve dello shop relativo all'user con ShopDAO.getShopFromUser
    public ShopBean getShopInfo() throws Exception {
        return shopBean;
    }

    //riempito manageShopPage (Alessandro)
    @FXML
    public void manageShopPage(TextField shopName, TextField shopAddress, TextField shopWorkers, TextField shopDescription){
        shopBean.setShopName(shopName.getText());
        //shopBean.setShopAddress(shopAddress.getText());
        //shopBean.setShopWorkers(shopWorkers.getText());
        shopBean.setShopDescription(shopDescription.getText());
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        try {
            manageShopPageController.updateData(this.shopBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void fillView(ShopBean bean){
        shopBean = bean;
        System.out.println("Filling View from ShopBean data passedBY TopBarHairdresserViewController");
        //quì riempirò i campi delle TextFile/TextArea/Label dell'fxml grazie ai getter della bean che mi è stata passata in ingresso
    }

}
