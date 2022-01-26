package cutit.controller.manageshoppage;

import cutit.bean.ShopBeanInterface;
import cutit.exception.WrongInputDataException;
import cutit.factory.AlertFactory;
import cutit.utils.TextFieldCheck;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalTime;
import java.util.*;

public class HairdresserManageShopPageViewController2 {

    private ShopBeanInterface shopBeanSecondUI;
    private ManageShopPageController manageShopPageController;
    private final List<CheckBox> checkBoxList = new ArrayList<>();
    private final List<ImageView> ivList = new ArrayList<>();
    private final Map<Integer, File> imageMap = new HashMap<>();

    @FXML
    private Label lblShopName;

    @FXML
    private TextField tfAddressStreet;

    @FXML
    private TextField tfAddressCity;

    @FXML
    private TextField tfAddressCAP;

    @FXML
    private TextField tfPhoneNumberPrefix;

    @FXML
    private TextField tfPhoneNumberSuffix;

    @FXML
    private CheckBox cbMon;

    @FXML
    private CheckBox cbTue;

    @FXML
    private CheckBox cbWed;

    @FXML
    private CheckBox cbThu;

    @FXML
    private CheckBox cbFri;

    @FXML
    private CheckBox cbSat;

    @FXML
    private CheckBox cbSun;

    @FXML
    private TextField tfOpenTime;

    @FXML
    private TextField tfCloseTime;

    @FXML
    private TextArea taDescription;

    @FXML
    private TextArea taEmployee;

    @FXML
    private ImageView iv1;

    @FXML
    private ImageView iv2;

    @FXML
    private ImageView iv3;

    @FXML
    private ImageView iv4;

    @FXML
    private ImageView iv5;

    @FXML
    private ImageView iv6;

    @FXML
    private ImageView iv7;

    @FXML
    private ImageView iv8;

    @FXML
    private Button btnUpdateShopData;

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
        ivList.add(iv1);
        ivList.add(iv2);
        ivList.add(iv3);
        ivList.add(iv4);
        ivList.add(iv5);
        ivList.add(iv6);
        ivList.add(iv7);
        ivList.add(iv8);
    }

    public void fillView(ShopBeanInterface shopBean){
        shopBeanSecondUI = shopBean;

        lblShopName.setText(shopBeanSecondUI.getShopName());
        if(shopBeanSecondUI.getShopAddress() != null){
            setAddress();
        }
        if(shopBeanSecondUI.getShopPhoneNumber() != null){
            setPhoneNumber();
        }
        if(shopBeanSecondUI.getShopDescription() != null){
            taDescription.setText(shopBeanSecondUI.getShopDescription());
        }
        if(shopBeanSecondUI.getShopEmployee() != null){
            taEmployee.setText(shopBeanSecondUI.getShopEmployee());
        }

        if(!shopBeanSecondUI.getShopOpenDays().isEmpty()){
            for(int i = 0; i< shopBeanSecondUI.getShopOpenDays().size(); i++){
                checkBoxList.get(i).setSelected(shopBeanSecondUI.getShopOpenDays().get(i+1));
            }
        }

        tfOpenTime.setText(shopBeanSecondUI.getShopOpenTime().toString());
        tfCloseTime.setText(shopBeanSecondUI.getShopCloseTime().toString());

        if(!shopBeanSecondUI.getImages().isEmpty()){
            for(int i = 0; i< shopBeanSecondUI.getImages().size(); i++){
                File file = shopBeanSecondUI.getImages().get(i);
                imageMap.put(i+1, file);
                ivList.get(i).setImage(new Image(String.valueOf(file.toURI())));
            }
        }
    }

    private void setPhoneNumber() {
        String prefix = shopBeanSecondUI.getShopPhoneNumber().substring(0,3);
        String suffix = shopBeanSecondUI.getShopPhoneNumber().substring(3,10);
        tfPhoneNumberPrefix.setText(prefix);
        tfPhoneNumberSuffix.setText(suffix);
    }

    private void setAddress(){
        StringTokenizer st = new StringTokenizer(shopBeanSecondUI.getShopAddress(), "-");
        String street = st.nextToken();
        String city = st.nextToken();
        String cap = st.nextToken();
        tfAddressStreet.setText(street);
        tfAddressCity.setText(city);
        tfAddressCAP.setText(cap);
    }

    @FXML
    public void saveData(){
        if(checkInput()){
            shopBeanSecondUI.setShopAddress(getAddress());
            shopBeanSecondUI.setShopPhoneNumber(getPhone());
            shopBeanSecondUI.setShopDescription(taDescription.getText());
            shopBeanSecondUI.setShopEmployee(taEmployee.getText());
            Map<Integer, Boolean> openDaysMap = getOpenDays(checkBoxList);
            shopBeanSecondUI.setShopOpenDays(openDaysMap);
            shopBeanSecondUI.setShopOpenTime(LocalTime.parse(tfOpenTime.getText()));
            shopBeanSecondUI.setShopCloseTime(LocalTime.parse(tfCloseTime.getText()));
            shopBeanSecondUI.setImages(getImages());
            try {
                manageShopPageController.updateShop(shopBeanSecondUI);
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.CONFIRMATION, "Updated", "Data successfully updated");
                alert.show();
            } catch (WrongInputDataException wde){
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.INFORMATION, "Information", wde.getMessage());
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
                alert.showAndWait();
            }
        }
    }

    private boolean checkInput(){
        boolean testOnAddress = checkAddress();
        boolean testOnPhoneNumber = checkPhoneNumber();
        boolean testOnOpenTime = checkTime(tfOpenTime.getText());
        boolean testOnCloseTime = checkTime(tfCloseTime.getText());
        return testOnAddress && testOnPhoneNumber && testOnOpenTime && testOnCloseTime;
    }

    private boolean checkAddress(){
        return (!Objects.equals(tfAddressStreet.getText(), "") && !Objects.equals(tfAddressCity.getText(), "") &&
                !Objects.equals(tfAddressCAP.getText(), "") &&
                TextFieldCheck.isInteger(tfAddressCAP.getText(), "CAP field must be an Integer number (ex. 00133, 00179, ...)"));
    }

    private boolean checkPhoneNumber(){
        return (!Objects.equals(tfPhoneNumberPrefix.getText(), "") && !Objects.equals(tfPhoneNumberSuffix.getText(), "") &&
                TextFieldCheck.isPhoneNumber(tfPhoneNumberPrefix.getText()+tfPhoneNumberSuffix.getText()));
    }

    private boolean checkTime(String time){
        return TextFieldCheck.isTimeFormat(time, "Time is not correct. Please follow the syntax hh:mm.");
    }

    private String getAddress(){
        return tfAddressStreet.getText() + "-" + tfAddressCity.getText() + "-" + tfAddressCAP.getText();
    }

    private String getPhone(){
        return tfPhoneNumberPrefix.getText()+tfPhoneNumberSuffix.getText();
    }

    private Map<Integer, Boolean> getOpenDays(List<CheckBox> checkBoxList) {
        Map<Integer, Boolean> map = new HashMap<>();
        for(int i = 0; i<checkBoxList.size(); i++){
            map.put(i+1, checkBoxList.get(i).isSelected());
        }
        return map;
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

    @FXML
    public void uploadImage(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage = (Stage) lblShopName.getScene().getWindow();
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
