package dravahoteli.dravahoteliapp.SceneControllers;

import dravahoteli.dravahoteliapp.Entities.Hotel;
import dravahoteli.dravahoteliapp.Entities.Soba;
import dravahoteli.dravahoteliapp.Entities.Stranka;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class ReservationSuccessSceneController {

    @FXML
    private BorderPane paneHotelListView;
    private Stranka currentUser;
    private HashMap<String, String> reservationInfo;
    private Hotel currentSelectedHotel;
    private Soba currentSelectedRoom;
    private String stKartice;

    @FXML
    void checkReceipt(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReceiptScene.fxml"));
        Parent root = loader.load();
        ReceiptSceneController rsc = loader.getController();
        rsc.onSwitchScene(currentUser, reservationInfo, currentSelectedRoom ,currentSelectedHotel, stKartice );
        Stage stage = new Stage();
        stage.setTitle("Račun");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void switchToMainMenuScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuScene.fxml"));
        Parent root = loader.load();

        MainMenuSceneController mainMenuSceneController = loader.getController();
        // Pass current signed-in user to the next controller (keeping track of the session user)
        mainMenuSceneController.onSwitchScene(currentUser);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("dravahoteli/dravahoteliapp/css/style.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void userHelp(ActionEvent event) {

    }

    public void onSwitchScene(Stranka currentUser, HashMap<String, String> reservationInfo, Soba currentSelectedRoom, Hotel currentSelectedHotel,String stKartice ) {
        this.currentUser = currentUser;
        this.reservationInfo = reservationInfo;
        this.currentSelectedHotel = currentSelectedHotel;
        this.currentSelectedRoom = currentSelectedRoom;
        this.stKartice = stKartice;
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
