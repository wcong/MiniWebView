package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.CookieStore;

public class Main extends Application {

    private Scene scene;

    final File cookieFile = new File("cookie");

    final CookieStore cookieStore = getCookieStore();

    @Override
    public void start(Stage stage) throws Exception {
        // create the scene
        stage.setTitle("Web View");
        scene = new Scene(new Browser(cookieStore), 750, 500, Color.web("#FFFFFF"));
        stage.setScene(scene);
        stage.show();
    }

    private CookieStore getCookieStore() {
        if (!cookieFile.exists()) {
            return null;
        }
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(cookieFile));
            return (CookieStore) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void stop() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(cookieFile));
            objectOutputStream.writeObject(cookieStore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
