package cutit.controller.manageshoppage;

import cutit.bean.interfaces.ShopBeanInterface;
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
    private List<CheckBox> checkBoxList;
    private List<ImageView> ivList;
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
    private CheckBox cbMonday;

    @FXML
    private CheckBox cbTuesday;

    @FXML
    private CheckBox cbWednesday;

    @FXML
    private CheckBox cbThursday;

    @FXML
    private CheckBox cbFriday;

    @FXML
    private CheckBox cbSaturday;

    @FXML
    private CheckBox cbSunday;

    @FXML
    private TextField tfOpenTime;

    @FXML
    private TextField tfCloseTime;

    @FXML
    private TextArea taDescription;

    @FXML
    private TextArea taEmployee;

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;

    @FXML
    private ImageView imageView4;

    @FXML
    private ImageView imageView5;

    @FXML
    private ImageView imageView6;

    @FXML
    private ImageView imageView7;

    @FXML
    private ImageView imageView8;

    @FXML
    public void initialize(){
        manageShopPageController = new ManageShopPageController();
        checkBoxList = Arrays.asList(cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday, cbSaturday, cbSunday);
        ivList = Arrays.asList(imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8);
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
            if (mouseEvent.getSource().equals(imageView1)) {
                imageMap.put(1, file);
                imageView1.setImage(image);
            }else if(mouseEvent.getSource().equals(imageView2)){
                imageMap.put(2, file);
                imageView2.setImage(image);
            }else if(mouseEvent.getSource().equals(imageView3)){
                imageMap.put(3, file);
                imageView3.setImage(image);
            }else if(mouseEvent.getSource().equals(imageView4)){
                imageMap.put(4, file);
                imageView4.setImage(image);
            }else if(mouseEvent.getSource().equals(imageView5)){
                imageMap.put(5, file);
                imageView5.setImage(image);
            }else if(mouseEvent.getSource().equals(imageView6)){
                imageMap.put(6, file);
                imageView6.setImage(image);
            }else if(mouseEvent.getSource().equals(imageView7)){
                imageMap.put(7, file);
                imageView7.setImage(image);
            }else if(mouseEvent.getSource().equals(imageView8)){
                imageMap.put(8, file);
                imageView8.setImage(image);
            }
        }
    }

}
