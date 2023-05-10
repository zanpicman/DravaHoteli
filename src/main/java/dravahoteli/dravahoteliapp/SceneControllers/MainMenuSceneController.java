package dravahoteli.dravahoteliapp.SceneControllers;

import dravahoteli.dravahoteliapp.Entities.Stranka;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuSceneController implements Initializable {
    /*
    ***********************************************
                OTHER ATTRIBUTES
    ***********************************************
    */
    private Stranka currentUser;

    /*
    ***********************************************
                    OTHER METHODS
    ***********************************************
     */
    /**
     * For some reason this method exists.
     */
    public void printUser() {
        System.out.println(currentUser);
    }

    /*
    ***********************************************
            SWITCHING SCENE METHODS
    ***********************************************
    */
    public void switchToHotelListScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HotelListScene.fxml"));
        Parent root = loader.load();

        HotelListSceneController hotelListSceneController = loader.getController();
        hotelListSceneController.onSwitchScene(currentUser);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("dravahoteli/dravahoteliapp/css/style.css");
        stage.setScene(scene);
        stage.show();
    }

    /*
    ***********************************************
                INITIALIZATION METHODS
    ***********************************************
     */
    public void onSwitchScene(Stranka currentUser) {
        // Saving the current user to this controllers attribute
        this.currentUser = currentUser;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void switchToLoginScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("dravahoteli/dravahoteliapp/css/style.css");
        stage.setScene(scene);
        stage.show();
    }
}
