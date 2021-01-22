package randevu.gui;

import javafx.scene.control.Alert;

public class GuiUtil {
    public static void hataGoster(String hataHeader,String hataMesaj) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("HATA");
        alert.setHeaderText(hataHeader);
        alert.setContentText(hataMesaj);

        alert.showAndWait();
    }

    public static void kayitBasariliMesajiGoster() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("BAŞARILI");
        alert.setHeaderText("Kayıt Başarılı");
        alert.setContentText("Kayıt Sisteme Aktarılmıştır");

        alert.showAndWait();
    }

}
