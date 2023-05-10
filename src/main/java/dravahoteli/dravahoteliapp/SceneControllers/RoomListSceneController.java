package dravahoteli.dravahoteliapp.SceneControllers;

import dravahoteli.dravahoteliapp.Boundaries.ZMStrankaOpraviRezervacijo;
import dravahoteli.dravahoteliapp.Entities.Hotel;
import dravahoteli.dravahoteliapp.Entities.Soba;
import dravahoteli.dravahoteliapp.Entities.Stranka;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.Double.MAX_VALUE;

public class RoomListSceneController implements Initializable {

    /*
    ***********************************************
            INJECTED FXML ATTRIBUTES
    ***********************************************
     */
    @FXML
    public VBox roomBox;

    @FXML
    public ImageView imageViewSelectedRoomImage;

    @FXML
    public Label labelSelectedRoomName;

    @FXML
    public Label labelSelectedRoomHotel;

    @FXML
    public Label labelSelectedRoomDescription;

    @FXML
    public Label labelSelectedRoomPricePerDay;

    @FXML
    public BorderPane paneRoomListView;


    /*
    ***********************************************
                    OTHER ATTRIBUTES
    ***********************************************
    */
    private Stranka currentUser;

    private Hotel currentSelectedHotel;

    private Soba currentSelectedRoom;

    private ArrayList<Soba> sobaArrayList;

    private ZMStrankaOpraviRezervacijo zmStrankaOpraviRezervacijo;


    /*
    ***********************************************
                OTHER METHODS
    ***********************************************
    */
    private void updateSelectedSobaInfo(Soba soba) {
        currentSelectedRoom = soba;

        String name = soba.getIme();
        String hotelName = currentSelectedHotel.getIme();
        String description = soba.getOpis();
        double pricePerDay = soba.getCenaDan();
        int numPersons = soba.getSteviloPostelj();
        Image image;

        String header = String.format("%s \uD83D\uDECF%d", name, numPersons);

        InputStream inputStream = getClass().getResourceAsStream(soba.getUrl());
        InputStream inputStreamBackup = getClass().getResourceAsStream("/dravahoteli/dravahoteliapp/images/missing_img.jpg");
        if (inputStream != null) image = new Image(Objects.requireNonNull(inputStream));
        else if (inputStreamBackup != null) image = new Image(Objects.requireNonNull(inputStreamBackup));
        else image = null;

        imageViewSelectedRoomImage.setImage(image);
        labelSelectedRoomName.setText(header);
        labelSelectedRoomHotel.setText("");
        labelSelectedRoomDescription.setText(description);
        labelSelectedRoomPricePerDay.setText(String.format("Cena na dan: %.2f", pricePerDay));
    }

    public void podrobnostiSobe(ActionEvent actionEvent) throws IOException {
        if (currentSelectedRoom == null) return;

        // Taking easy way: currentSelectedRoom. -> easy way (passing attributes in next method via this one)
        switchToRoomDetailsScene(actionEvent);
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


    private void switchToRoomDetailsScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomDetailsScene.fxml"));

        Parent root = loader.load();

        RoomDetailsSceneController roomDetailsSceneController = loader.getController();
        roomDetailsSceneController.onSwitchScene(currentUser, currentSelectedHotel, currentSelectedRoom);

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
   private void prikaziSobe() {
       roomBox.getChildren().clear();
       roomBox.setSpacing(10);
       for (Soba soba : sobaArrayList) {
           Button button = new Button(soba.getIme());
           button.setOnAction(event -> updateSelectedSobaInfo(soba));
           button.setMaxWidth(MAX_VALUE);
           roomBox.getChildren().add(button);
       }
   }

    public void onSwitchScene(Stranka currentUser, Hotel currentSelectedHotel, ArrayList<Soba> currentSelectedRooms) {
        this.currentUser = currentUser;
        this.currentSelectedHotel = currentSelectedHotel;
        this.sobaArrayList = currentSelectedRooms;

        labelSelectedRoomHotel.setText("Izbran hotel: " + currentSelectedHotel.getIme());

        // PD METODA (12) - Napolni kolekcijo z novimi entitetami hotelov
        prikaziSobe();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        zmStrankaOpraviRezervacijo = new ZMStrankaOpraviRezervacijo();

        // Poenostavi vse vrednosti elementov
        imageViewSelectedRoomImage.setImage(null);
        labelSelectedRoomName.setText("Izberite poljubno sobo");
        labelSelectedRoomHotel.setText("");
        labelSelectedRoomDescription.setText("");
        labelSelectedRoomPricePerDay.setText("");
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
