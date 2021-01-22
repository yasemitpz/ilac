package randevu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import randevu.db.model.Doktor;
import randevu.db.model.Hasta;
import randevu.db.model.Kisi;
import randevu.gui.DoktorController;
import randevu.gui.HastaController;

import java.io.IOException;
import java.util.List;

public class Controller {
    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private void login(ActionEvent event) {
        event.consume();
        Main.loggedUser = null;

        List<Kisi> kisiler = Main.dbrepo.getKisiler();


        String user = userName.getText();
        String pass = password.getText();

        for (Kisi kisi : kisiler) {
            if (kisi.getAd().equals(user) && kisi.getSifre().equals(pass)) {
                Main.loggedUser = kisi;
                break;
            }
        }

        if (Main.loggedUser != null) {
            System.out.println("Giriş Başarılı");
            if (Main.loggedUser instanceof Hasta) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("HastaGui.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();

                    HastaController controller = fxmlLoader.getController();
                    controller.setName((Hasta) Main.loggedUser);

                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setTitle("Hasta Randevu Ekranı");
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (Main.loggedUser instanceof Doktor) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("DoktorGui.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();

                    DoktorController controller = fxmlLoader.getController();
                    controller.setName((Doktor) Main.loggedUser);

                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setTitle("Doktor Randevu Ekranı");

                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}