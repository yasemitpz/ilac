package randevu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import randevu.db.DBRepository;
import randevu.db.model.Kisi;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main extends Application {
    public static  DBRepository dbrepo;
    public static Kisi loggedUser;
    @Override
    public void start(Stage primaryStage) throws Exception{
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        dbrepo = new DBRepository(entityManager);

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                entityManager.close();
                Platform.exit();
                System.exit(0);
            }
        });

        primaryStage.setTitle("HASTANE RANDEVU");
        primaryStage.setScene(new Scene(root, 402, 262));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
