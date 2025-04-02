package co.edu.uniquindio.banco.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginControlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField IdTextField;

    @FXML
    private Button IniciarSesionButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {
        assert IdTextField != null : "fx:id=\"IdTextField\" was not injected: check your FXML file 'login.fxml'.";
        assert IniciarSesionButton != null : "fx:id=\"IniciarSesionButton\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'login.fxml'.";

    }

}
