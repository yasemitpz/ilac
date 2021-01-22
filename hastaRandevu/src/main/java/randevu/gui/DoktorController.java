package randevu.gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import randevu.DateUtil;
import randevu.Main;
import randevu.db.model.*;
import randevu.gui.widget.RandevuController;
import randevu.gui.widget.ZamanCizelgesi;

import java.util.ArrayList;
import java.util.List;

public class DoktorController {
    @FXML
    VBox doktorAnaPanel;

    @FXML
    Button btnClose;


    @FXML
    Label lblIsimSoyisim;

    @FXML
    ComboBox<Hasta> cbHastalar;

    @FXML
    ComboBox<Ilac> cbIlaclar;

    @FXML
    public void initialize() {
        ZamanCizelgesi zamanCizelgesi = new ZamanCizelgesi();
        doktorAnaPanel.getChildren().add(zamanCizelgesi);
        fixRandevuButtons(zamanCizelgesi);

        RandevuController controller = zamanCizelgesi.getController();
        controller.getDatePicker().valueProperty().addListener((ov, oldValue, newValue) -> {
            fixRandevuButtons(zamanCizelgesi);
        });

        List<Hasta> hastalar = Main.dbrepo.getHastalar();
        List<Ilac> ilaclar = Main.dbrepo.getIlaclar();

        cbHastalar.setItems(FXCollections.observableList(hastalar));
        cbIlaclar.setItems(FXCollections.observableList(ilaclar));

    }

    private void fixRandevuButtons( ZamanCizelgesi zamanCizelgesi) {
        List<RandevuZaman> doktorsRandavus = getDoktorsRandavus();
        RandevuController controller = zamanCizelgesi.getController();

        for (RandevuZaman randavus : doktorsRandavus) {
            String tarih = DateUtil.toString(randavus.getZaman());
            if(randavus.getDoktor().getId() == ((Doktor)Main.loggedUser).getId()){
                if (controller.getSeciliZaman().equals(tarih)) {
                    int saat = DateUtil.getSaat(randavus.getZaman());
                    Button button = null;
                    switch (saat) {
                        case 9:
                            button = zamanCizelgesi.getController().getSaat9();
                            break;
                        case 10:
                            button = zamanCizelgesi.getController().getSaat10();
                            break;
                        case 11:
                            button = zamanCizelgesi.getController().getSaat11();
                            break;
                        case 13:
                            button = zamanCizelgesi.getController().getSaat13();
                            break;
                        case 14:
                            button = zamanCizelgesi.getController().getSaat14();
                            break;
                        case 15:
                            button = zamanCizelgesi.getController().getSaat15();
                            break;
                        case 16:
                            button = zamanCizelgesi.getController().getSaat16();
                            break;
                        case 17:
                            button = zamanCizelgesi.getController().getSaat17();
                            break;
                    }
                    button.setDisable(true);
                    button.setTextFill(Color.RED);
                    button.setText(randavus.getHasta().getAd()+" "+randavus.getHasta().getSoyad());
                }
            }
        }

    }


    private List<RandevuZaman> getDoktorsRandavus() {
        Doktor doktor = (Doktor) Main.loggedUser;

        List<RandevuZaman> randevuList = new ArrayList<>();

        List<RandevuZaman> randevuZamanlari = Main.dbrepo.getRandevuZamanlari();
        for (RandevuZaman randevuZaman : randevuZamanlari) {
            if (randevuZaman.getDoktor().getId() == doktor.getId()) {
                randevuList.add(randevuZaman);
            }
        }

        return randevuList;
    }


    public void setName(Doktor doktorName){
        lblIsimSoyisim.setText(doktorName.toString());
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ilacYaz(ActionEvent event){
        if(cbIlaclar.getSelectionModel().getSelectedItem() == null && cbHastalar.getSelectionModel().getSelectedItem() == null){
            GuiUtil.hataGoster("Ilac Kayıt Hatası","ilaç veya hasta seçilmemiş");
            return;
        }

        Doktor d = (Doktor) Main.loggedUser;
        Hasta h = cbHastalar.getSelectionModel().getSelectedItem();
        Ilac i = cbIlaclar.getSelectionModel().getSelectedItem();

        HastaIlac hastaIlac = new HastaIlac(d,h,i);
        Main.dbrepo.hastaIlacKaydet(hastaIlac);

        GuiUtil.kayitBasariliMesajiGoster();
    }
}
