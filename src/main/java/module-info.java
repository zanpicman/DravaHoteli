module dravahoteli.dravahoteliapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens dravahoteli.dravahoteliapp to javafx.fxml;
    exports dravahoteli.dravahoteliapp;
    exports dravahoteli.dravahoteliapp.Controllers;
    opens dravahoteli.dravahoteliapp.Controllers to javafx.fxml;
    exports dravahoteli.dravahoteliapp.Boundaries;
    opens dravahoteli.dravahoteliapp.Boundaries to javafx.fxml;
    exports dravahoteli.dravahoteliapp.Entities;
    exports dravahoteli.dravahoteliapp.SceneControllers;
    opens dravahoteli.dravahoteliapp.SceneControllers;
}