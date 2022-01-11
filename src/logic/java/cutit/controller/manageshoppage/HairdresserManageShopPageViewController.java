package cutit.controller.manageshoppage;

import cutit.bean.ShopBean;
import cutit.checkTest.checkTextField;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.*;

public class HairdresserManageShopPageViewController {

    private ShopBean shopBeanFirstUI;
    private ManageShopPageController manageShopPageController;
    private List<CheckBox> checkBoxList = new ArrayList<>();
    private List<ImageView> ivList = new ArrayList<>();
    private Map<Integer, File> imageMap = new HashMap<>();

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
        if(checkTextField.isNumeric(tfPhoneNumber.getText(),"Error!", "Not Panic!", "You have to insert numbers in the phone number field") && tfPhoneNumber.getText().length() < 12 ) {
            shopBeanFirstUI.setAddress(tfAddress.getText());
            shopBeanFirstUI.setPhoneNumber(tfPhoneNumber.getText());
            shopBeanFirstUI.setShopDescription(taDescription.getText());
            shopBeanFirstUI.setEmployee(taEmployee.getText());
            Map<Integer, Boolean> openDaysMap = getOpenDays(checkBoxList);
            shopBeanFirstUI.setOpenDays(openDaysMap);
            shopBeanFirstUI.setOpenTime(LocalTime.parse(cbOpenTime.getValue()));
            shopBeanFirstUI.setCloseTime(LocalTime.parse(cbCloseTime.getValue()));
            shopBeanFirstUI.setImages(getImages());
            try {
                manageShopPageController.updateShop(shopBeanFirstUI);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<File> getImages() {
        List<File> images = new ArrayList<>();
        for(int i = 1; i<imageMap.size()+1; i++){
            if(imageMap.containsKey(i)){
                images.add(imageMap.get(i));
            }
        }
        return images;
    }


    public void fillView(ShopBean bean){
        shopBeanFirstUI = bean;

        shopName.setText(shopBeanFirstUI.getShopName());
        if(shopBeanFirstUI.getAddress() != null){
            tfAddress.setText(shopBeanFirstUI.getAddress());
        }
        if(shopBeanFirstUI.getPhoneNumber() != null){
            tfPhoneNumber.setText(shopBeanFirstUI.getPhoneNumber());
        }
        if(shopBeanFirstUI.getShopDescription() != null){
            taDescription.setText(shopBeanFirstUI.getShopDescription());
        }
        if(shopBeanFirstUI.getEmployee() != null){
            taEmployee.setText(shopBeanFirstUI.getEmployee());
        }

        if(!shopBeanFirstUI.getOpenDays().isEmpty()){
            for(int i = 0; i< shopBeanFirstUI.getOpenDays().size(); i++){
                checkBoxList.get(i).setSelected(shopBeanFirstUI.getOpenDays().get(i+1));
            }
        }

        cbOpenTime.setValue(shopBeanFirstUI.getOpenTime().toString());
        cbCloseTime.setValue(shopBeanFirstUI.getCloseTime().toString());

       if(!shopBeanFirstUI.getImages().isEmpty()){
            for(int i = 0; i< shopBeanFirstUI.getImages().size(); i++){
                File file = shopBeanFirstUI.getImages().get(i);
                imageMap.put(i+1, file);
                ivList.get(i).setImage(new Image(String.valueOf(file.toURI())));
            }
        }
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

    public void uploadImage(javafx.scene.input.MouseEvent mouseEvent) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage = (Stage)shopName.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if(file != null){
            Image image = new Image(String.valueOf(file.toURI()));
            if (mouseEvent.getSource().equals(iv1)) {
                imageMap.put(1, file);
                iv1.setImage(image);
            }else if(mouseEvent.getSource().equals(iv2)){
                imageMap.put(2, file);
                iv2.setImage(image);
            }else if(mouseEvent.getSource().equals(iv3)){
                imageMap.put(3, file);
                iv3.setImage(image);
            }else if(mouseEvent.getSource().equals(iv4)){
                imageMap.put(4, file);
                iv4.setImage(image);
            }else if(mouseEvent.getSource().equals(iv5)){
                imageMap.put(5, file);
                iv5.setImage(image);
            }else if(mouseEvent.getSource().equals(iv6)){
                imageMap.put(6, file);
                iv6.setImage(image);
            }else if(mouseEvent.getSource().equals(iv7)){
                imageMap.put(7, file);
                iv7.setImage(image);
            }else if(mouseEvent.getSource().equals(iv8)){
                imageMap.put(8, file);
                iv8.setImage(image);
            }
        }
    }
}
