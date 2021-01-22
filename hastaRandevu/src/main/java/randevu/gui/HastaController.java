package randevu.gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import randevu.DateUtil;
import randevu.Main;
import randevu.db.DBRepository;
import randevu.db.model.Doktor;
import randevu.db.model.Hasta;
import randevu.db.model.RandevuZaman;
import randevu.gui.widget.RandevuController;
import randevu.gui.widget.ZamanCizelgesi;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


public class HastaController {

    @FXML
    VBox hastaAnaPanel;

    @FXML
    Button btnClose;

    @FXML
    ComboBox<Doktor> cbDoktorlar;

    @FXML
    Label lblIsimSoyisim;

    @FXML
    public void initialize() {
        ZamanCizelgesi zamanCizelgesi = new ZamanCizelgesi();
        hastaAnaPanel.getChildren().add(zamanCizelgesi);


        List<Doktor> doktorlar = Main.dbrepo.getDoktorlar();
        cbDoktorlar.setItems(FXCollections.observableList(doktorlar));

        cbDoktorlar.valueProperty().addListener(e -> {
            Doktor doktor = cbDoktorlar.getSelectionModel().getSelectedItem();

            fixRandevuButtons(doktor, zamanCizelgesi);
        });

        enableAllButton(zamanCizelgesi);
    }

    private void fixRandevuButtons(Doktor doktor, ZamanCizelgesi zamanCizelgesi) {
        enableAllButton(zamanCizelgesi);
        List<RandevuZaman> doktorsRandavus = getDoktorsRandavus(doktor);
        RandevuController controller = zamanCizelgesi.getController();

        for (RandevuZaman randavus : doktorsRandavus) {
            String tarih = DateUtil.toString(randavus.getZaman());

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
            }
        }

    }

    private void enableAllButton(ZamanCizelgesi zamanCizelgesi) {
        zamanCizelgesi.getController().getSaat9().setDisable(false);
        zamanCizelgesi.getController().getSaat10().setDisable(false);
        zamanCizelgesi.getController().getSaat11().setDisable(false);
        zamanCizelgesi.getController().getSaat13().setDisable(false);
        zamanCizelgesi.getController().getSaat14().setDisable(false);
        zamanCizelgesi.getController().getSaat15().setDisable(false);
        zamanCizelgesi.getController().getSaat16().setDisable(false);
        zamanCizelgesi.getController().getSaat17().setDisable(false);

        zamanCizelgesi.getController().getSaat9().setTextFill(Color.rgb(0, 255, 55));
        zamanCizelgesi.getController().getSaat10().setTextFill(Color.rgb(0, 255, 55));
        zamanCizelgesi.getController().getSaat11().setTextFill(Color.rgb(0, 255, 55));
        zamanCizelgesi.getController().getSaat13().setTextFill(Color.rgb(0, 255, 55));
        zamanCizelgesi.getController().getSaat14().setTextFill(Color.rgb(0, 255, 55));
        zamanCizelgesi.getController().getSaat15().setTextFill(Color.rgb(0, 255, 55));
        zamanCizelgesi.getController().getSaat16().setTextFill(Color.rgb(0, 255, 55));
        zamanCizelgesi.getController().getSaat17().setTextFill(Color.rgb(0, 255, 55));

        zamanCizelgesi.getController().getSaat9().setOnAction(e -> {
            randevuAl(zamanCizelgesi, 9);
        });

        zamanCizelgesi.getController().getSaat10().setOnAction(e -> {
            randevuAl(zamanCizelgesi, 10);
        });

        zamanCizelgesi.getController().getSaat11().setOnAction(e -> {
            randevuAl(zamanCizelgesi, 11);
        });
        zamanCizelgesi.getController().getSaat13().setOnAction(e -> {
            randevuAl(zamanCizelgesi, 13);
        });
        zamanCizelgesi.getController().getSaat14().setOnAction(e -> {
            randevuAl(zamanCizelgesi, 14);
        });
        zamanCizelgesi.getController().getSaat15().setOnAction(e -> {
            randevuAl(zamanCizelgesi, 15);
        });
        zamanCizelgesi.getController().getSaat16().setOnAction(e -> {
            randevuAl(zamanCizelgesi, 16);
        });
        zamanCizelgesi.getController().getSaat17().setOnAction(e -> {
            randevuAl(zamanCizelgesi, 17);
        });

    }


    @Transactional
    private void randevuAl(ZamanCizelgesi zamanCizelgesi, int saat) {
        if(cbDoktorlar.getSelectionModel().getSelectedItem() == null){
            GuiUtil.hataGoster("Randevu Kayıt Hatası","Doktor seçimi yapılmadı");
            return;
        }

        String tarih = zamanCizelgesi.getController().getSeciliZaman();
        String yeniTarih = tarih + " " + padSaat(saat) + ":00";

        RandevuZaman randevuZaman = new RandevuZaman(DateUtil.toTimestamp(yeniTarih), cbDoktorlar.getSelectionModel().getSelectedItem(), (Hasta) Main.loggedUser);
        Main.dbrepo.randevuKaydet(randevuZaman);

        GuiUtil.kayitBasariliMesajiGoster();

        fixRandevuButtons(cbDoktorlar.getSelectionModel().getSelectedItem() ,zamanCizelgesi);
    }


    private String padSaat(int saat) {
        if (saat == 9) {
            return "0" + 9;
        }
        return saat + "";
    }


    private List<RandevuZaman> getDoktorsRandavus(Doktor doktor) {
        List<RandevuZaman> randevuList = new ArrayList<>();

        List<RandevuZaman> randevuZamanlari = Main.dbrepo.getRandevuZamanlari();
        for (RandevuZaman randevuZaman : randevuZamanlari) {
            if (randevuZaman.getDoktor().getId() == doktor.getId()) {
                randevuList.add(randevuZaman);
            }
        }

        return randevuList;
    }

    public void setName(Hasta hastaName){
        lblIsimSoyisim.setText(hastaName.toString());
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
