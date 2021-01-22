package randevu.gui.widget;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import randevu.DateUtil;

import java.time.LocalDate;

public class RandevuController {
    @FXML
    Button saat9;
    @FXML
    Button saat10;
    @FXML
    Button saat11;
    @FXML
    Button saat13;
    @FXML
    Button saat14;
    @FXML
    Button saat15;
    @FXML
    Button saat16;
    @FXML
    Button saat17;
    @FXML
    DatePicker datePicker;



    @FXML
    public void initialize() {
        datePicker.setValue(LocalDate.now());
    }

    public String getSeciliZaman(){
        return DateUtil.toString(datePicker.getValue());
    }

    public Button getSaat9() {
        return saat9;
    }

    public Button getSaat10() {
        return saat10;
    }

    public Button getSaat11() {
        return saat11;
    }

    public Button getSaat13() {
        return saat13;
    }

    public Button getSaat14() {
        return saat14;
    }

    public Button getSaat15() {
        return saat15;
    }

    public Button getSaat16() {
        return saat16;
    }

    public Button getSaat17() {
        return saat17;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }
}
