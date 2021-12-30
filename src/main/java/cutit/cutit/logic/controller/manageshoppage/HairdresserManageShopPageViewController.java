package cutit.cutit.logic.controller.manageshoppage;

import cutit.cutit.logic.bean.ShopBean;
import cutit.cutit.logic.model.Shop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.File;
import java.time.LocalTime;
import java.util.*;

public class HairdresserManageShopPageViewController {

    private ShopBean shopBean;
    private ManageShopPageController manageShopPageController;
    private List<CheckBox> checkBoxList = new ArrayList<>();
    private List<ImageView> ivList = new ArrayList<>();
    private Map<Integer, File> mapFile = new HashMap<>();

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
    private ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8;

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
        ivList.add(iv1);
        ivList.add(iv2);
        ivList.add(iv3);
        ivList.add(iv4);
        ivList.add(iv5);
        ivList.add(iv6);
        ivList.add(iv7);
        Image im = new Image(Objects.requireNonNull(getClass().getResource("/cutit/cutit/files/file_upload.png"), "Unable to get resource file /cutit/cutit/files/file_upload.png").toString());
        for(int j=0;j<ivList.size();j++){
            ivList.get(j).setImage(im);
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
        shopBean.setImages(getImages());
        try {
            manageShopPageController.updateShop(shopBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<File> getImages() {
        List<File> images = new ArrayList<>();
        for(int i = 0;i<mapFile.size();i++){
            images.add(mapFile.get(i));
        }
        return images;
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

        /*if(!shopBean.getImages().isEmpty()){
            for(int i = 0; i<shopBean.getImages().size(); i++){
                ivList.get(i).setImage(new Image(String.valueOf(shopBean.getImages().get(i).toURI())));
            }
        }*/
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

    public void uploadImage(javafx.scene.input.MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage = (Stage)shopName.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if(file != null){
            Image image = new Image(String.valueOf(file.toURI()));
            if (mouseEvent.getSource().equals(iv1)) {
                mapFile.put(1, file);
                iv1.setImage(image);
            }else if(mouseEvent.getSource().equals(iv2)){
                mapFile.put(2, file);
                iv2.setImage(image);
            }else if(mouseEvent.getSource().equals(iv3)){
                mapFile.put(3, file);
                iv3.setImage(image);
            }else if(mouseEvent.getSource().equals(iv4)){
                mapFile.put(4, file);
                iv4.setImage(image);
            }else if(mouseEvent.getSource().equals(iv5)){
                mapFile.put(5, file);
                iv5.setImage(image);
            }else if(mouseEvent.getSource().equals(iv6)){
                mapFile.put(6, file);
                iv6.setImage(image);
            }else if(mouseEvent.getSource().equals(iv7)){
                mapFile.put(7, file);
                iv7.setImage(image);
            }else if(mouseEvent.getSource().equals(iv8)){
                mapFile.put(8, file);
                iv8.setImage(image);
            }
        }
    }
}
