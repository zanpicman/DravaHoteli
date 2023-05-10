package dravahoteli.dravahoteliapp.Boundaries;

import dravahoteli.dravahoteliapp.Controllers.KOpraviRezervacijo;
import dravahoteli.dravahoteliapp.Entities.Hotel;
import dravahoteli.dravahoteliapp.Entities.Soba;
import dravahoteli.dravahoteliapp.Entities.Stranka;
import dravahoteli.dravahoteliapp.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.Double.MAX_VALUE;

public class ZMStrankaOpraviRezervacijo implements Initializable {

    public KOpraviRezervacijo kOpraviRezervacijo;

    public Stranka prijava(String uporabniskoIme, String geslo) throws IOException {
        kOpraviRezervacijo = new KOpraviRezervacijo();
        return kOpraviRezervacijo.prijaviUporabnika(uporabniskoIme, geslo);
    }

    public ArrayList<Hotel> preglejHotele() {
        kOpraviRezervacijo = new KOpraviRezervacijo();
        return kOpraviRezervacijo.vrniSeznamHotelov();
    }


    public java.lang.Object[] prikaziSobeHotela() {
        // TODO: implement
        return null;
    }


    public ArrayList<Soba> izberiHotel(int hid) {
        kOpraviRezervacijo = new KOpraviRezervacijo();
        return kOpraviRezervacijo.vrniSeznamSobHotela(hid);
    }


    public void podrobnostiSobe() {
        // TODO: implement
    }


    public int prikaziPodrobnostiSobe() {
        // TODO: implement
        return 0;
    }


    public void rezerviraj() {
        // TODO: implement
    }


    public boolean preglejRazporozljivost() {
        // TODO: implement
        return false;
    }


    public java.lang.Object[] prikaziRazporozljivosti() {
        // TODO: implement
        return null;
    }


    public boolean rezervirajSobo(String ime,
                                  String priimek,
                                  String stevilkaKartice,
                                  String stevilkaCcv,
                                  String mesecPoteka,
                                  String letoPoteka,
                                  LocalDate datumOd,
                                  LocalDate datumDo,
                                  int steviloOseb,
                                  double znesek,
                                  String loginId,
                                  int sid) {
        KOpraviRezervacijo kOpraviRezervacijo = new KOpraviRezervacijo();
        return kOpraviRezervacijo.opraviRezervacijo(ime, priimek, stevilkaKartice, stevilkaCcv, mesecPoteka,
                letoPoteka, datumOd, datumDo, steviloOseb, znesek, loginId, sid
        );
    }


    public int pokaziNapacnaPrijava() {
        // TODO: implement
        return 0;
    }


    public int pokaziBlokiranaPrijava() {
        // TODO: implement
        return 0;
    }


    /*
    ************************************************
                    OTHER ATTRIBUTES
    ***********************************************
     */

    private Stage stage;

    private Scene scene;

    private Parent root;


    /*
    ************************************************
                    OTHER METHODS
    ***********************************************
     */
    public void setFlaggedState(Control control) {
        control.getStyleClass().add("flagged");
    }

    public void unsetFlaggedState(Control control) {
        control.getStyleClass().remove("flagged");
    }

    public void unsetFlaggedStateMouseEvent(MouseEvent mouseEvent) {
        Control control = (Control) mouseEvent.getSource();
        unsetFlaggedState(control);
    }

    public void unsetFlaggedStateKeyEvent(KeyEvent keyEvent) {
        Control control = (Control) keyEvent.getSource();
        unsetFlaggedState(control);
    }

    private boolean isFlaggedState(Control control) {
        return control.getStyleClass().contains("flagged");
    }



    /*
    ************************************************
            METHODS FOR SWITCHING SCENES
    ***********************************************
     */

    public void switchToSceneLogin(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Views/LoginScene.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("dravahoteli/dravahoteliapp/css/style.css");

        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneMainMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Views/MainMenuScene.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("dravahoteli/dravahoteliapp/css/style.css");

        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneHotelListView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Views/HotelListScene.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("dravahoteli/dravahoteliapp/css/style.css");

        stage.setScene(scene);
        stage.show();
    }


    /*
    ***********************************************
    THIS CONTROLLER CLASS HANDLES MORE THAN ONE SCENE.
    THIS IS CONSIDERED A BAD PRACTICE AND SHOULD NOT BE IMITATED.
    THIS IS WHY WE ARE CONSTANTLY CHECKING IF CERTAIN PANES ARE NULL, AS IF THEY ARE, WE ARE NOT ON THE PARENT SCENE.
    FOR EDUCATIONAL PURPOSES ONLY ;)
    ***********************************************
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // HANDLING RoomListView

        // HANDLING RoomDetailsView

        // HANDLING ReservationFormView

        // HANDLING ReservationSuccessView

        // HANDLING ReservationFailureView

        // HANDLING HotelView.fxml
        //if (hotelBox != null) hotelListViewHandler();
    }


    private void hotelListViewHandler() {

    }

    private void roomListViewHandler() {

    }

    private void roomDetailsViewHandler() {

    }

    private void reservationFormViewHandler() {

    }

    private void reservationSuccessViewHandler() {

    }

    private void reservationFailureViewHandler() {

    }


}