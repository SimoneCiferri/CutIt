package cutit.controller.manageshoppage;

import cutit.bean.ShopBeanInterface;
import cutit.exception.DBConnectionException;
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
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.*;

public class HairdresserManageShopPageViewController {

    private ShopBeanInterface shopBeanFirstUI;
    private ManageShopPageController manageShopPageController;
    private List<CheckBox> checkBoxList = new ArrayList<>();
    private List<ImageView> ivList = new ArrayList<>();
    private static final String CONNECTION_ERROR_TITLE = "Connection error";
    private static final String WARNING_TITLE = "Warning";
    private static final String ERROR_TITLE = "Error";
    private static final String CONNECTION_ERROR_MESSAGE = "Please check your internet connection. If problem persists try to restart the application.";
    private static final String SQL_ERROR_MESSAGE = "Please check your internet connection. If problem persists contact us at cutitapp@support.com.";
    private static final String FILE_NOT_FOUND_ERROR_MESSAGE = "An error occurred handling some files. Please try again.";
    private final Map<Integer, File> imageMap = new HashMap<>();

    @FXML
    private Label shopName;

    @FXML
    private TextField  tfPhoneNumber;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextArea taDescription;

    @FXML
    private TextArea taEmployee;

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
    private ChoiceBox<LocalTime> cbOpenTime;

    @FXML
    private ChoiceBox<LocalTime> cbCloseTime;

    @FXML
    private ImageView ivProfPhoto;

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
    private ImageView  iv7;

    @FXML
    private ImageView iv8;

    @FXML
    public void initialize(){
        manageShopPageController = new ManageShopPageController();
        checkBoxList = Arrays.asList(cbMon, cbTue, cbWed, cbThu, cbFri, cbSat, cbSun);
        List<LocalTime> timeList = new ArrayList<>();
        LocalTime l;
        for(int i = 7; i<=21;i++){
            if(i<10){
                l = LocalTime.parse("0"+ i + ":00:00");
            }else{
                l = LocalTime.parse(i + ":00:00");
            }
            timeList.add(l);
            timeList.add(l.plusMinutes(30));
        }
        for (LocalTime s : timeList) {
            cbOpenTime.getItems().add(s);
            cbCloseTime.getItems().add(s);
        }
        ivList = Arrays.asList(iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8);
    }

    @FXML
    public void updateData(){
        if(TextFieldCheck.isPhoneNumber(tfPhoneNumber.getText()) && TextFieldCheck.checkAddress(tfAddress.getText())) {
            shopBeanFirstUI.setShopAddress(tfAddress.getText());
            shopBeanFirstUI.setShopPhoneNumber(tfPhoneNumber.getText());
            shopBeanFirstUI.setShopDescription(taDescription.getText());
            shopBeanFirstUI.setShopEmployee(taEmployee.getText());
            Map<Integer, Boolean> openDaysMap = getOpenDays(checkBoxList);
            shopBeanFirstUI.setShopOpenDays(openDaysMap);
            shopBeanFirstUI.setShopOpenTime(cbOpenTime.getValue());
            shopBeanFirstUI.setShopCloseTime(cbCloseTime.getValue());
            shopBeanFirstUI.setImages(getImages());
            try {
                manageShopPageController.updateShop(shopBeanFirstUI);
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.CONFIRMATION, "Updated", "Data successfully updated");
                alert.show();
            } catch (WrongInputDataException e){
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, WARNING_TITLE, e.getMessage());
                alert.showAndWait();
            } catch(DBConnectionException dbe){
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, CONNECTION_ERROR_MESSAGE);
                alert.showAndWait();
            } catch (SQLException sqle) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, SQL_ERROR_MESSAGE);
                alert.showAndWait();
            }catch (FileNotFoundException fnf) {
                Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, ERROR_TITLE, FILE_NOT_FOUND_ERROR_MESSAGE);
                alert.showAndWait();
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


    public void fillView(ShopBeanInterface bean){
        shopBeanFirstUI = bean;

        shopName.setText(shopBeanFirstUI.getShopName());
        if(shopBeanFirstUI.getShopAddress() != null){
            tfAddress.setText(shopBeanFirstUI.getShopAddress());
        }
        if(shopBeanFirstUI.getShopPhoneNumber() != null){
            tfPhoneNumber.setText(shopBeanFirstUI.getShopPhoneNumber());
        }
        if(shopBeanFirstUI.getShopDescription() != null){
            taDescription.setText(shopBeanFirstUI.getShopDescription());
        }
        if(shopBeanFirstUI.getShopEmployee() != null){
            taEmployee.setText(shopBeanFirstUI.getShopEmployee());
        }

        if(!shopBeanFirstUI.getShopOpenDays().isEmpty()){
            for(int i = 0; i< shopBeanFirstUI.getShopOpenDays().size(); i++){
                checkBoxList.get(i).setSelected(shopBeanFirstUI.getShopOpenDays().get(i+1));
            }
        }

        cbOpenTime.setValue(shopBeanFirstUI.getShopOpenTime());
        cbCloseTime.setValue(shopBeanFirstUI.getShopCloseTime());

       if(!shopBeanFirstUI.getImages().isEmpty()){
            for(int i = 0; i< shopBeanFirstUI.getImages().size(); i++){
                File file = shopBeanFirstUI.getImages().get(i);
                if(i == 0){
                    ivProfPhoto.setImage(new Image(String.valueOf(file.toURI())));
                }
                imageMap.put(i+1, file);
                ivList.get(i).setImage(new Image(String.valueOf(file.toURI())));
            }
        }
    }

    private Map<Integer, Boolean> getOpenDays(List<CheckBox> checkBoxList) {
        Map<Integer, Boolean> map = new HashMap<>();
        for(int i = 0; i<checkBoxList.size(); i++){
            map.put(i+1, checkBoxList.get(i).isSelected());
        }
        return map;
    }

    @FXML
    public void uploadImage(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage = (Stage)shopName.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if(file != null){
            Image image = new Image(String.valueOf(file.toURI()));
            if (mouseEvent.getSource().equals(iv1) || mouseEvent.getSource().equals(ivProfPhoto)) {
                imageMap.put(1, file);
                iv1.setImage(image);
                ivProfPhoto.setImage(image);
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
