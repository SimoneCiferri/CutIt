package cutit.cutit.logic.controller.manageshoppage;

import cutit.cutit.logic.bean.ShopBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HairdresserManageShopPageViewController {

    private ShopBean shopBean;
    private ManageShopPageController manageShopPageController;

    @FXML
    private Label shopName;

    @FXML
    private TextField  tfPhoneNumber, tfAddress;

    @FXML
    private TextArea taDescription, taEmployee;

    @FXML
    public void initialize(){
        manageShopPageController = new ManageShopPageController();
        System.out.println("CONTROLLER GRAFICO HAIRDRESSERMANAGESHOPPAGEVIEWCONTROLLER");
    }

    @FXML
    public void updateData(){
        shopBean.setAddress(tfAddress.getText());
        shopBean.setPhoneNumber(tfPhoneNumber.getText());
        shopBean.setShopDescription(taDescription.getText());
        shopBean.setEmployee(taEmployee.getText());
        try {
            manageShopPageController.updateShop(shopBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillView(ShopBean bean){
        shopBean = bean;

        shopName.setText(shopBean.getShopName());
        if(shopBean.getAddress() != null){
            tfAddress.setText(shopBean.getAddress());
        }
        if(shopBean.getPhoneNumber() != null){
            tfPhoneNumber.setText(shopBean.getPhoneNumber());
        }
        if(shopBean.getShopDescription() != null){
            taDescription.setText(shopBean.getShopDescription());
        }
        if(shopBean.getEmployee() != null){
            taEmployee.setText(shopBean.getEmployee());
        }
        System.out.println("Filling View from ShopBean data passedBY TopBarHairdresserViewController");
    }

}
