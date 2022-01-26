package cutit.controller.manageshoppage;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class HairdresserManageShopPageViewController2 {

    @FXML
    private Label lblShopName;

    @FXML
    private TextField tfAddressStreet;

    @FXML
    private TextField tdAddressCity;

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
    public void initialize(){
        System.out.println(" Starting ---> HairdresserManageShopPageViewController2");
    }
}
