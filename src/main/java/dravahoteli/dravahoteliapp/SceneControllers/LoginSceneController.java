package dravahoteli.dravahoteliapp.SceneControllers;

import dravahoteli.dravahoteliapp.Boundaries.ZMStrankaOpraviRezervacijo;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class LoginSceneController implements Initializable {

    /*
    ***********************************************
                INJECTED FXML ATTRIBUTES
    ***********************************************
     */
    @FXML
    public TextField textFieldUsername;

    @FXML
    public PasswordField passwordFieldPassword;

    @FXML
    public Label labelWrongPassword;
    public Button prijava;
    public Label labelTooManyInputs;

    /*
    ***********************************************
                    OTHER ATTRIBUTES
    ***********************************************
     */
    // Represents the main "interface - zaslonska maska"
    private ZMStrankaOpraviRezervacijo zmStrankaOpraviRezervacijo;

    // Represents the current signed-in user
    private Stranka currentUser;
    private int steviloNeuspelihVnosov = 0;


    /*
    ***********************************************
                    VALIDATION METHODS
    ***********************************************
     */

    private void setFlaggedStateLoginInput(Control control) {
        control.getStyleClass().remove("loginInput");
        control.getStyleClass().add("loginFlagged");
    }

    public void unsetFlaggedStateLoginInput(Control control) {
        control.getStyleClass().add("loginInput");
        control.getStyleClass().remove("loginFlagged");
    }

    public void unsetFlaggedStateLoginInputMouseEvent(MouseEvent mouseEvent) {
        Control control = (Control) mouseEvent.getSource();
        unsetFlaggedStateLoginInput(control);
    }

    public void unsetFlaggedStateLoginInputKeyboardEvent(KeyEvent keyEvent) {
        Control control = (Control) keyEvent.getSource();
        unsetFlaggedStateLoginInput(control);
    }

    private boolean isFlaggedStateLoginInput(Control control) {
        return control.getStyleClass().contains("loginFlagged");
    }

    private boolean checkAnyFlaggedLoginForm() {
        return isFlaggedStateLoginInput(textFieldUsername) ||
                isFlaggedStateLoginInput(passwordFieldPassword);
    }

    private boolean validateUsername() {
        return !textFieldUsername.getText().equals("");
    }

    private boolean validatePassword() {
        return !passwordFieldPassword.getText().equals("");
    }

    private boolean validateSignInForm() {
        if (!validateUsername()) setFlaggedStateLoginInput(textFieldUsername);
        if (!validatePassword()) setFlaggedStateLoginInput(passwordFieldPassword);
        return !checkAnyFlaggedLoginForm();
    }

    /*
    ***********************************************
                    OTHER METHODS
    ***********************************************
     */
    public void login(ActionEvent actionEvent) throws IOException {
        // Check if form is valid
        if (!validateSignInForm()) return;

        String username = textFieldUsername.getText();
        String password = passwordFieldPassword.getText();

        Stranka stranka = zmStrankaOpraviRezervacijo.prijava(username, password);
        // Check if Stranka with that username exists AND if the passwords matches
        if (stranka != null) {
            currentUser = stranka;
            switchToMainMenuScene(actionEvent);
        } else {
            //System.out.println("napačno geslo ali uporabniško ime");
            labelWrongPassword.setVisible(true);
            steviloNeuspelihVnosov++;
            if (steviloNeuspelihVnosov == 3) {
                labelTooManyInputs.setVisible(true);
                labelWrongPassword.setVisible(false);
                prijava.setDisable(true);
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        steviloNeuspelihVnosov = 0;
                        prijava.setDisable(false);
                        labelTooManyInputs.setVisible(false);
                    }
                },   60 * 10 * 1000); // 10 minut
            }
        }
    }

    /*
    ***********************************************
                SWITCHING SCENE METHODS
    ***********************************************
     */
    // PD: ZMStrankaOpraviRezervacijo->pokaziGlavniMeni()
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

    /*
    ***********************************************
                INITIALIZATION METHODS
    ***********************************************
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        zmStrankaOpraviRezervacijo = new ZMStrankaOpraviRezervacijo();

        textFieldUsername.getStyleClass().add("loginInput");
        passwordFieldPassword.getStyleClass().add("loginInput");
    }
}
