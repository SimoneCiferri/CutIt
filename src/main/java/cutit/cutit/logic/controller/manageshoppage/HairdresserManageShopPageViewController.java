package cutit.cutit.logic.controller.manageshoppage;

import cutit.cutit.logic.bean.ShopBean;
import cutit.cutit.logic.bean.UserBean;
import cutit.cutit.logic.database.dao.ServiceDAO;
import cutit.cutit.logic.database.dao.ShopDAO;
import cutit.cutit.logic.database.dao.UserDAO;
import cutit.cutit.logic.model.Service;
import cutit.cutit.logic.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

public class HairdresserManageShopPageViewController {

    private ShopBean shopBean;
    private User user;
    private UserBean userBean;
    private ManageShopPageController manageShopPageController;

    @FXML
    private TextField  tfShopName, phoneNumberHairdresser, tfDescription;

    @FXML
    private Button btnSave;

    @FXML
    public void initialize(){
        tfShopName.setText(shopBean.getShopName());
        phoneNumberHairdresser.setText(shopBean.getPhoneNumber());
        tfDescription.setText(shopBean.getShopDescription());
        manageShopPageController = new ManageShopPageController();
        System.out.println("CONTROLLER GRAFICO HAIRDRESSERMANAGESHOPPAGEVIEWCONTROLLER");
    }

    //riempito manageShopPage (Alessandro)
    @FXML
    public void manageShopPage(){
    }

    public void fillView(ShopBean bean){
        shopBean = bean;
        System.out.println("Filling View from ShopBean data passedBY TopBarHairdresserViewController");
        //quì riempirò i campi delle TextFile/TextArea/Label dell'fxml grazie ai getter della bean che mi è stata passata in ingresso
    }

}
