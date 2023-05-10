package dravahoteli.dravahoteliapp.SceneControllers;

import dravahoteli.dravahoteliapp.Boundaries.ZMStrankaOpraviRezervacijo;
import dravahoteli.dravahoteliapp.Entities.Hotel;
import dravahoteli.dravahoteliapp.Entities.Rezervacija;
import dravahoteli.dravahoteliapp.Entities.Soba;
import dravahoteli.dravahoteliapp.Entities.Stranka;
import dravahoteli.dravahoteliapp.Seed.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.regex.Pattern;

public class ReservationSceneController {

    /*
    ***********************************************
            INJECTED FXML ATTRIBUTES
    ***********************************************
    */
    @FXML
    private Label birthDayLabel;

    @FXML
    private TextField cardNumberIF;

    @FXML
    private TextField ccvNumberIF;

    @FXML
    private Label dateArrivalLabel;

    @FXML
    private Label dateDepartureLabel;

    @FXML
    private Label emailAddressLabel;

    @FXML
    private TextField expiryMonthIF;

    @FXML
    private TextField expiryYearIF;

    @FXML
    private Label homeAddressLabel;

    @FXML
    private Label hotelLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label numberPeopleLabel;

    @FXML
    private BorderPane paneHotelListView;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label roomLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Label warnningLabel;

    /*
    ***********************************************
                    OTHER ATTRIBUTES
    ***********************************************
     */
    private Stranka currentUser;

    private Hotel currentSelectedHotel;

    private Soba currentSelectedRoom;

    private HashMap<String, String> reservationInfo;

    /*
     ***********************************************
                     VALIDATION METHODS
     ***********************************************
      */
    private boolean validateCustomerCardNumber() {
        String cardNumber = cardNumberIF.getText();
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$");
        return pattern.matcher(cardNumber).matches();
    }

    private boolean validateCustomerCCVNumber() {
        String ccvNumber = ccvNumberIF.getText();
        Pattern pattern = Pattern.compile("^\\d{3}$");
        return pattern.matcher(ccvNumber).matches();
    }


    private boolean validateCustomerExpiryYear() {
        String expiryYear = expiryYearIF.getText();
        Pattern pattern = Pattern.compile("^\\d{2}$");
        return pattern.matcher(expiryYear).matches();
    }

    private boolean validateCustomerExpiryMonth() {
        String expiryMonth = expiryMonthIF.getText();
        Pattern pattern = Pattern.compile("^\\d{2}$");
        return pattern.matcher(expiryMonth).matches();
    }


    private boolean validateForm() {
        if (!validateCustomerCardNumber()) setFlaggedState(cardNumberIF);
        if (!validateCustomerCCVNumber()) setFlaggedState(ccvNumberIF);
        if (!validateCustomerExpiryMonth()) setFlaggedState(expiryMonthIF);
        if (!validateCustomerExpiryYear()) setFlaggedState(expiryYearIF);

        return !checkAnyFlagged();
    }


    private boolean checkAnyFlagged() {
        return isFlaggedState(cardNumberIF) ||
                isFlaggedState(ccvNumberIF) ||
                isFlaggedState(expiryMonthIF) ||
                isFlaggedState(expiryYearIF);
    }


    public void setFlaggedState(Control control) {
        control.getStyleClass().add("flagged");
    }

    public void unsetFlaggedState(Control control) {
        control.getStyleClass().remove("flagged");
    }

    private boolean isFlaggedState(Control control) {
        return control.getStyleClass().contains("flagged");
    }

    public void unsetFlaggedStateMouseEvent(MouseEvent mouseEvent) {
        Control control = (Control) mouseEvent.getSource();
        unsetFlaggedState(control);
    }

    public void unsetFlaggedStateKeyEvent(KeyEvent keyEvent) {
        Control control = (Control) keyEvent.getSource();
        unsetFlaggedState(control);
    }

    /*
    ***********************************************
                    OTHER METHODS
    ***********************************************
     */
    @FXML
    void rezervirajSobo(ActionEvent event) throws IOException {
        boolean isFormValid = validateForm();
        if (!isFormValid) {
            return;
        }

        // opravi plačilo (poveži se z SV)
        ZMStrankaOpraviRezervacijo zmStrankaOpraviRezervacijo = new ZMStrankaOpraviRezervacijo();

        // TODO: tuki bi se lahko dobilo nazaj objekt Rezervacija in se to predalo naslednjemu kontrolerju za izpis računa
        boolean opravljenoPlacilo = zmStrankaOpraviRezervacijo.rezervirajSobo(
                currentUser.getIme(),
                currentUser.getPriimek(),
                cardNumberIF.getText(),
                ccvNumberIF.getText(),
                expiryMonthIF.getText(),
                expiryYearIF.getText(),
                LocalDate.parse(reservationInfo.get("date_of_arrival")),
                LocalDate.parse(reservationInfo.get("date_of_departure")),
                Integer.parseInt(reservationInfo.get("number_people")),
                Double.parseDouble(reservationInfo.get("total_price")),
                currentUser.getLoginID(),
                currentSelectedRoom.getSid()
        );

        if (!opravljenoPlacilo)
            warnningLabel.setText("Napaka pri preverjanju podatkov o kartici. Prosimo preverite podatke kartice in poskusite ponovno.");
        else {
            switchToRoomReservationSuccessScene(event);
        }

    }

    /*
    ***********************************************
                SWITCHING SCENE METHODS
    ***********************************************
     */
    private void switchToRoomReservationSuccessScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationSuccessScene.fxml"));
        Parent root = loader.load();


        ReservationSuccessSceneController reservationSceneController = loader.getController();
        reservationSceneController.onSwitchScene(currentUser, reservationInfo,  currentSelectedRoom, currentSelectedHotel, cardNumberIF.getText());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("dravahoteli/dravahoteliapp/css/style.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML

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
    public void onSwitchScene(Stranka currentUser,
                              Hotel currentSelectedHotel,
                              Soba currentSelectedRoom,
                              HashMap<String, String> reservationInfo) {
        this.currentUser = currentUser;
        this.currentSelectedHotel = currentSelectedHotel;
        this.currentSelectedRoom = currentSelectedRoom;
        this.reservationInfo = reservationInfo;

        afterInitialize();
    }

    private void afterInitialize() {
        hotelLabel.setText(currentSelectedHotel.getIme());
        roomLabel.setText(currentSelectedRoom.getIme());
        numberPeopleLabel.setText(reservationInfo.get("number_people"));
        dateArrivalLabel.setText(reservationInfo.get("date_of_arrival"));
        dateDepartureLabel.setText(reservationInfo.get("date_of_departure"));
        totalPriceLabel.setText(String.format("%.0f €", Double.parseDouble(reservationInfo.get("total_price"))));
        nameLabel.setText(currentUser.getIme());
        surnameLabel.setText(currentUser.getPriimek());
        birthDayLabel.setText(currentUser.getDatumRojstva().toString());
        homeAddressLabel.setText(currentUser.getDomaciNaslov());
        emailAddressLabel.setText(currentUser.getGmail());
        phoneLabel.setText(currentUser.getTelefon());
        warnningLabel.setText("");
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
