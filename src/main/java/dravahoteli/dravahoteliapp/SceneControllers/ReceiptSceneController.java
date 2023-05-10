package dravahoteli.dravahoteliapp.SceneControllers;

import dravahoteli.dravahoteliapp.Entities.Hotel;
import dravahoteli.dravahoteliapp.Entities.Soba;
import dravahoteli.dravahoteliapp.Entities.Stranka;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class ReceiptSceneController {
    public Label hotel;

    public Label soba;
    public Label stOseb;

    public Label ime;
    public Label prihod;
    public Label odhod;
    public Label kartica;
    public Label znesek;
    private HashMap<String, String> reservationInfo;
    private Stranka currentUser;
    private Hotel currentSelectedHotel;
    private Soba currentSelectedRoom;

    private String stKartice;
    public void onSwitchScene(Stranka currentUser, HashMap<String, String> reservationInfo, Soba currentSelectedRoom, Hotel currentSelectedHotel , String stKartice ) {
        this.currentUser = currentUser;
        this.reservationInfo = reservationInfo;
        this.currentSelectedHotel = currentSelectedHotel;
        this.currentSelectedRoom = currentSelectedRoom;
        this.stKartice = stKartice;
        afterInitialize();
    }
    private void afterInitialize() {

        hotel.setText(currentSelectedHotel.getIme());
        soba.setText(currentSelectedRoom.getIme());
        stOseb.setText(reservationInfo.get("number_people"));
        prihod.setText(reservationInfo.get("date_of_arrival"));
        odhod.setText(reservationInfo.get("date_of_departure"));
        znesek.setText(String.format("%.0f €", Double.parseDouble(reservationInfo.get("total_price"))));
        ime.setText(currentUser.getIme()+ " " + currentUser.getPriimek());
        kartica.setText("XXXX-XXXX-XXXX-" + stKartice.substring(15,19));
    }
}
