package cutit.cutit.logic.controller.manageshoppage;

import cutit.cutit.logic.bean.ShopBean;
import cutit.cutit.logic.model.Shop;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HairdresserManageShopPageViewController {

    private ShopBean shopBean;
    private ManageShopPageController manageShopPageController;
    private List<CheckBox> checkBoxList = new ArrayList<>();

    @FXML
    private Label shopName;

    @FXML
    private TextField  tfPhoneNumber, tfAddress;

    @FXML
    private TextArea taDescription, taEmployee;

    @FXML
    private CheckBox cbMon, cbTue, cbWed, cbThu, cbFri, cbSat, cbSun;

    @FXML
    private ChoiceBox<String> cbOpenTime, cbCloseTime;

    @FXML
    public void initialize(){
        manageShopPageController = new ManageShopPageController();
        checkBoxList.add(cbMon);
        checkBoxList.add(cbTue);
        checkBoxList.add(cbWed);
        checkBoxList.add(cbThu);
        checkBoxList.add(cbFri);
        checkBoxList.add(cbSat);
        checkBoxList.add(cbSun);
        List<String> timeList = new ArrayList<>();
        timeList.add("08:00");
        timeList.add("20:00");
        for (String s : timeList) {
            cbOpenTime.getItems().add(s);
            cbCloseTime.getItems().add(s);
        }
        System.out.println("CONTROLLER GRAFICO HAIRDRESSERMANAGESHOPPAGEVIEWCONTROLLER");
    }

    @FXML
    public void updateData(){
        shopBean.setAddress(tfAddress.getText());
        shopBean.setPhoneNumber(tfPhoneNumber.getText());
        shopBean.setShopDescription(taDescription.getText());
        shopBean.setEmployee(taEmployee.getText());
        Map<Integer, Boolean> openDaysMap = getOpenDays(checkBoxList);
        shopBean.setOpenDays(openDaysMap);
        shopBean.setOpenTime(LocalTime.parse(cbOpenTime.getValue()));
        shopBean.setCloseTime(LocalTime.parse(cbCloseTime.getValue()));
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

        if(!shopBean.getOpenDays().isEmpty()){
            for(int i = 0; i<shopBean.getOpenDays().size(); i++){
                checkBoxList.get(i).setSelected(shopBean.getOpenDays().get(i+1));
            }
        }

        cbOpenTime.setValue(shopBean.getOpenTime().toString());
        cbCloseTime.setValue(shopBean.getCloseTime().toString());
        System.out.println("Filling View from ShopBean data passedBY TopBarHairdresserViewController");
    }

    private Map<Integer, Boolean> getOpenDays(List<CheckBox> checkBoxList) {
        Map<Integer, Boolean> map = new HashMap<>();
        for(int i = 0; i<checkBoxList.size(); i++){
            if(checkBoxList.get(i).isSelected()){
                map.put(i+1, true);
            }else{
                map.put(i+1, false);
            }
        }
        return map;
    }

}
