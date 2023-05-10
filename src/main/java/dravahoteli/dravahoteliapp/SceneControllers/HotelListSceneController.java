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
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.Double.MAX_VALUE;

public class HotelListSceneController implements Initializable {
    //additi on
    /*
    ***********************************************
                INJECTED FXML ATTRIBUTES
    ***********************************************
     */

    @FXML
    private BorderPane paneHotelListView;

    @FXML
    private VBox hotelBox;

    @FXML
    public ImageView imageViewSelectedHotel;

    @FXML
    public Label labelSelectedHotelName;

    @FXML
    public Label labelSelectedHotelAddress;

    @FXML
    public Label labelSelectedHotelDescription;

    /*
    ***********************************************
                    OTHER ATTRIBUTES
    ***********************************************
     */
    private ZMStrankaOpraviRezervacijo zmStrankaOpraviRezervacijo;

    private ArrayList<Hotel> hotelArrayList;

    private Hotel currentSelectedHotel;

    private Stranka currentUser;

    private ArrayList<Soba> currentSelectedRooms;

    /*
    ***********************************************
                    OTHER METHODS
    ***********************************************
     */
    // METHOD OF ZMStrankaOpraviRezervacijo
    public void izberiHotel(ActionEvent actionEvent) throws IOException {
        if (currentSelectedHotel == null) return;

        // currentSelectedHotel.getSoba() -> easy way
        currentSelectedRooms = zmStrankaOpraviRezervacijo.izberiHotel(currentSelectedHotel.getHid());
        switchToRoomListScene(actionEvent);
    }

    /**
     * Updates the current selected hotel pane with the selected hotel information.
     *
     * @param hotel
     */
    private void updateSelectedHotelInfo(Hotel hotel) {
        currentSelectedHotel = hotel;

        String name = hotel.getIme();
        String address = hotel.getLokacija();
        String description = hotel.getOpis();
        int numStars = hotel.getZvezdice();
        String header = String.format("%s (%s)", name, "★".repeat(numStars));
        Image image = null;

        InputStream inputStream = getClass().getResourceAsStream("/dravahoteli/dravahoteliapp/images/hotellistimage_" + hotel.getHid() + ".jpg");
        InputStream inputStreamBackup = getClass().getResourceAsStream("/dravahoteli/dravahoteliapp/images/missing_img.jpg");
        if (inputStream != null) image = new Image(Objects.requireNonNull(inputStream));
        else if (inputStreamBackup != null) image = new Image(Objects.requireNonNull(inputStreamBackup));

        if (image != null) imageViewSelectedHotel.setImage(image);
        labelSelectedHotelName.setText(header);
        labelSelectedHotelAddress.setText(address);
        labelSelectedHotelDescription.setText(description);
    }

    /*
    ***********************************************
                SWITCHING SCENE METHODS
    ***********************************************
     */
    private void switchToRoomListScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomListScene.fxml"));

        Parent root = loader.load();

        RoomListSceneController roomListSceneController = loader.getController();
        roomListSceneController.onSwitchScene(currentUser, currentSelectedHotel, currentSelectedRooms);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("dravahoteli/dravahoteliapp/css/style.css");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainMenuScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuScene.fxml"));

        Parent root = loader.load();

        MainMenuSceneController mainMenuSceneController = loader.getController();
        mainMenuSceneController.onSwitchScene(currentUser);

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
    // METHOD OF ZMStrankaOpraviRezervacijo
    public void prikaziHotele() {
        hotelBox.getChildren().clear();
        hotelBox.setSpacing(10);
        for (Hotel hotel : hotelArrayList) {
            Button button = new Button(hotel.getIme());
            button.setOnAction(event -> updateSelectedHotelInfo(hotel));
            button.setMaxWidth(MAX_VALUE);
            hotelBox.getChildren().add(button);
        }
    }

    public void onSwitchScene(Stranka currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        zmStrankaOpraviRezervacijo = new ZMStrankaOpraviRezervacijo();

        // Pridobi seznam hotelov
        hotelArrayList = zmStrankaOpraviRezervacijo.preglejHotele();

        // Napolni kolekcijo z novimi entitetami hotelov
        prikaziHotele();

        // Poenostavi vse vrednosti elementov
        imageViewSelectedHotel.setImage(null);
        labelSelectedHotelName.setText("Izberite poljuben hotel");
        labelSelectedHotelAddress.setText("");
        labelSelectedHotelDescription.setText("");
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
