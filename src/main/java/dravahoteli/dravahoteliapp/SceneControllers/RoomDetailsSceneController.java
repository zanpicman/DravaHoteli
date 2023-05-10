package dravahoteli.dravahoteliapp.SceneControllers;

import dravahoteli.dravahoteliapp.Entities.Hotel;
import dravahoteli.dravahoteliapp.Entities.Rezervacija;
import dravahoteli.dravahoteliapp.Entities.Soba;
import dravahoteli.dravahoteliapp.Entities.Stranka;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class RoomDetailsSceneController implements Initializable {
    /*
    ***********************************************
            INJECTED FXML ATTRIBUTES
    ***********************************************
    */
    @FXML
    public ComboBox<Integer> numberOfPeopleCB;

    @FXML
    public DatePicker arrivalDateDP;

    @FXML
    public Label labelDateWarning;

    @FXML
    public DatePicker departureDateDP;

    @FXML
    public Label labelDailyPrice;

    @FXML
    public Label labelDailyPriceWithDays;

    @FXML
    public Label labelTotalPrice;

    @FXML
    public Label labelRoomBalcony;

    @FXML
    public Label labelRoomDescription;

    @FXML
    public ImageView roomImageIV;

    @FXML
    public Label labelRoomName;

    @FXML
    public BorderPane paneHotelListView;

    @FXML
    public Button reservationButton;

    /*
    ***********************************************
                    OTHER ATTRIBUTES
    ***********************************************
     */
    private Stranka currentUser;

    private Hotel currentSelectedHotel;

    private Soba currentSelectedRoom;

    private long daysBetween;

    boolean areDatesValid;

    double totalPrice;

    /*
    ***********************************************
                    VALIDATION METHODS
    ***********************************************
     */
    private boolean validateReservationDates(LocalDate dateOfArrival, LocalDate dateOfDeparture, javafx.scene.control.Control control) {
        // Arrival date is before today date
        if (control == arrivalDateDP && dateOfArrival != null && dateOfArrival.isBefore(LocalDate.now())) {
            labelDateWarning.setText("Napaka: datum rezervacije mora biti izbran za današnjim datumom.");
            setFlaggedState(arrivalDateDP);
            return false;
        }
        // Departure date is before today date
        if (control == departureDateDP && departureDateDP != null && dateOfDeparture.isBefore(LocalDate.now())) {
            labelDateWarning.setText("Napaka: datum odhoda mora biti izbran za današnjim datumom.");
            setFlaggedState(departureDateDP);
            return false;
        }

        // Check if both of them are set (TO VALIDATE IF EACH OF THEM IS IN CORRECT FORMAT OR EMPTY USE VALIDATOR)
        if (dateOfArrival == null || dateOfDeparture == null) {
            return false;
        }

        // Check if the dates are the same (customer can not make a reservation for the same day)
        if (dateOfArrival.equals(dateOfDeparture)) {
            labelDateWarning.setText("Napaka: stranka ne more rezervirati sobe za manj kot eno nočitev.");

            setFlaggedState(arrivalDateDP);
            setFlaggedState(departureDateDP);
            return false;
        }

        // Check if the dates are set in the correct manner (return date is before rental date)
        if (dateOfDeparture.isBefore(dateOfArrival)) {
            labelDateWarning.setText("Napaka: datum odhoda je pred datumom za izposojo.");

            setFlaggedState(departureDateDP);
            return false;
        }

        return true;
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


    public void unsetFlaggedStateKeyEvent(KeyEvent keyEvent) {
        Control control = (Control) keyEvent.getSource();
        unsetFlaggedState(control);
    }

    public void unsetFlaggedStateMouseEvent(MouseEvent mouseEvent) {
        Control control = (Control) mouseEvent.getSource();
        unsetFlaggedState(control);
    }

    /*
    ***********************************************
                    OTHER METHODS
    ***********************************************
     */
    public void updateDate(ActionEvent actionEvent) {
        daysBetween = 0;
        updatePriceRanges();  // Possibly redundant, need to find a better way to refresh if Dates mess up

        LocalDate dateOfArrival = arrivalDateDP.getValue();
        LocalDate dateOfDeparture = departureDateDP.getValue();

        areDatesValid = validateReservationDates(dateOfArrival, dateOfDeparture, (javafx.scene.control.Control) actionEvent.getTarget());
        if (!areDatesValid) return;

        boolean areDatesHit = areDatesHitInDB(dateOfArrival, dateOfDeparture);
        if (areDatesHit) {
            labelDateWarning.setText("Napaka pri izbiri datuma");
            return;
        }

        labelDateWarning.setText("");
        daysBetween = ChronoUnit.DAYS.between(dateOfArrival, dateOfDeparture);
        updatePriceRanges();
    }

    @FXML
    private void updatePriceRanges() {
        if (daysBetween != 0 && numberOfPeopleCB.getValue() != null) {
            int numPeople = numberOfPeopleCB.getValue();  // TODO: number of people should affect price
            int roomDailyPrice = (int) currentSelectedRoom.getCenaDan();
            totalPrice = daysBetween * roomDailyPrice;

            labelDailyPrice.setText(String.format("%d€ na noč", roomDailyPrice));
            labelDailyPriceWithDays.setText(String.format("%d € X %d nočitev", roomDailyPrice, daysBetween));
            labelTotalPrice.setText(String.format("%d €", (int) totalPrice));

            reservationButton.setDisable(false);
        } else {
            labelDailyPrice.setText(String.format("%d€ na noč", (int) currentSelectedRoom.getCenaDan()));
            labelDailyPriceWithDays.setText("Izberite želen termin");
            labelTotalPrice.setText("");

            reservationButton.setDisable(true);
        }
    }

    public void rezervirajSobo(ActionEvent actionEvent) throws IOException {
        // Če datumi niso pravilni uporabiku javimo napako
        if (!areDatesValid) {
            // Če datumi niso pravilni, pokličemo metodo za validacijo datumov, da se nepravilnilost obarva
            validateReservationDates(arrivalDateDP.getValue(), departureDateDP.getValue(), null);
            return;
        }
        // Če število oseb ni izbrano
        if (numberOfPeopleCB.getValue() == null) {
            labelDateWarning.setText("Izberite število oseb, prosim.");
            return;
        }
        switchToReservationSuccessScene(actionEvent);
    }

    /*
    ***********************************************
                SWITCHING SCENE METHODS
    ***********************************************
     */
    public void switchToRoomListScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomListScene.fxml"));
        Parent root = loader.load();

        RoomListSceneController roomListSceneController = loader.getController();
        roomListSceneController.onSwitchScene(currentUser, currentSelectedHotel, currentSelectedHotel.vrniSeznamSobHotela(currentSelectedHotel.getHid()));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("dravahoteli/dravahoteliapp/css/style.css");
        stage.setScene(scene);
        stage.show();

    }
    public void switchToReservationSuccessScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationScene.fxml"));
        Parent root = loader.load();

        // Handling controllers data exchange
        LocalDate dateOfArrival = arrivalDateDP.getValue();
        LocalDate dateOfDeparture = departureDateDP.getValue();

        HashMap<String, String> reservationInfo = new HashMap<>();
        reservationInfo.put("date_of_arrival", dateOfArrival.toString());
        reservationInfo.put("date_of_departure", dateOfDeparture.toString());
        reservationInfo.put("number_people", String.valueOf(numberOfPeopleCB.getValue()));
        reservationInfo.put("total_price", String.valueOf(totalPrice));

        ReservationSceneController reservationSceneController = loader.getController();
        reservationSceneController.onSwitchScene(currentUser, currentSelectedHotel, currentSelectedRoom, reservationInfo);

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
    private boolean isDateHitInDB(LocalDate date) {
        HashSet<Rezervacija> reservations = currentSelectedRoom.getRezervacija();

        for (Rezervacija reservation : reservations) {
            LocalDate dateOfArrival = reservation.getDatumOd();
            LocalDate dateOfDeparture = reservation.getDatumDo();
            if (!(date.isBefore(dateOfArrival) || date.isAfter(dateOfDeparture))) {
                return true;
            }
        }
        return false;
    }

    private boolean areDatesHitInDB(LocalDate userDOA, LocalDate userDOD) {
        HashSet<Rezervacija> reservations = currentSelectedRoom.getRezervacija();
        for (Rezervacija reservation : reservations) {
            LocalDate reservationDOA = reservation.getDatumOd();
            LocalDate reservationDOD = reservation.getDatumDo();
            if (!((userDOD.isBefore(reservationDOD) && userDOD.isBefore(reservationDOA) && userDOA.isBefore(reservationDOD) && userDOA.isBefore(reservationDOA)) ||
                    (userDOD.isAfter(reservationDOD) && userDOD.isAfter(reservationDOA) && userDOA.isAfter(reservationDOD)) && userDOA.isAfter(reservationDOA))) {
                return true;
            }
        }
        return false;
    }

    /**
     * https://stackoverflow.com/questions/62513192/javafx-datepicker-disable-future-dates
     */
    private void initializeDatePickers() {
        arrivalDateDP.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (empty || date.compareTo(LocalDate.now()) < 0) {
                    setDisable(true);
                    return;
                }

                boolean isHit = isDateHitInDB(date);
                setDisable(isHit);

            }
        });

        departureDateDP.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (empty || date.compareTo(LocalDate.now()) < 0) {
                    setDisable(true);
                    return;
                }

                boolean isHit = isDateHitInDB(date);
                setDisable(isHit);

            }
        });
    }

    /**
     * Neke vrste initializable metoda -> se izvede po initializabe in s tem dobi parametre iz predhodnega
     * kontrolerja!
     */
    private void podrobnostiSobe() {
        // Initialize header
        String header = String.format("%s \uD83D\uDECF%d", currentSelectedRoom.getIme(), currentSelectedRoom.getSteviloPostelj());
        labelRoomName.setText(header);

        // Initialize description
        labelRoomDescription.setText(currentSelectedRoom.getOpis());

        // Initialize balcony
        if (currentSelectedRoom.isBalkon()) labelRoomBalcony.setText("Balkon: Da");
        else labelRoomBalcony.setText("Balkon: Ne");

        // Initialize image
        Image image = null;
        InputStream inputStream = getClass().getResourceAsStream(currentSelectedRoom.getUrl());
        InputStream inputStreamBackup = getClass().getResourceAsStream("/dravahoteli/dravahoteliapp/images/missing_img.jpg");
        if (inputStream != null) image = new Image(Objects.requireNonNull(inputStream));
        else if (inputStreamBackup != null) image = new Image(Objects.requireNonNull(inputStreamBackup));
        roomImageIV.setImage(image);

        // Initialize number of people
        ArrayList<Integer> numberOfPeopleArray = new ArrayList<>();
        for (int i = 1; i <= currentSelectedRoom.getSteviloPostelj(); i++) {
            numberOfPeopleArray.add(i);
        }
        ObservableList<Integer> numberOfPeopleOptions = FXCollections.observableArrayList(numberOfPeopleArray);
        numberOfPeopleCB.setItems(numberOfPeopleOptions);


        // Initialize prices
        labelDailyPrice.setText(String.format("%d€ na noč", (int) currentSelectedRoom.getCenaDan()));
        labelDailyPriceWithDays.setText("Izberite želen termin");
        labelTotalPrice.setText("");
        labelDateWarning.setText("");
    }

    public void onSwitchScene(Stranka currentUser, Hotel currentSelectedHotel, Soba currentSelectedRoom) {
        this.currentUser = currentUser;
        this.currentSelectedHotel = currentSelectedHotel;
        this.currentSelectedRoom = currentSelectedRoom;

        podrobnostiSobe();
        initializeDatePickers();
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
