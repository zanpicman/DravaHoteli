package dravahoteli.dravahoteliapp;

import dravahoteli.dravahoteliapp.Seed.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        DB.fillDatabase();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SceneControllers/LoginScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 650);
        scene.getStylesheets().add("dravahoteli/dravahoteliapp/css/style.css");
        stage.setTitle("Drava Hoteli");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}