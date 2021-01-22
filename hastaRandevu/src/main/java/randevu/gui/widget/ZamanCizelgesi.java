package randevu.gui.widget;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ZamanCizelgesi extends Parent {
    RandevuController controller;

    public ZamanCizelgesi() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Randevu.fxml"));
        try {
            getChildren().add(fxmlLoader.load());

            controller = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RandevuController getController() {
        return controller;
    }
}
