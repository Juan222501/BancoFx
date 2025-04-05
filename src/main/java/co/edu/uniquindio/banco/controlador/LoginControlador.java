package co.edu.uniquindio.banco.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.banco.modelo.entidades.Banco;
import co.edu.uniquindio.banco.modelo.entidades.Sesion;
import co.edu.uniquindio.banco.modelo.entidades.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    private final Banco banco = Banco.getInstance();
    Sesion sesion = Sesion.getInstancia(); // Singleton

    @FXML
    void initialize() {
        assert IdTextField != null : "fx:id=\"IdTextField\" was not injected.";
        assert IniciarSesionButton != null : "fx:id=\"IniciarSesionButton\" was not injected.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected.";

        IniciarSesionButton.setOnAction(this::iniciarSesion);
    }

    private void iniciarSesion(ActionEvent actionEvent) {
        try {
            String id = IdTextField.getText();
            String password = passwordField.getText();

            if (id.isEmpty() || password.isEmpty()) {
                crearAlerta("Por favor, complete todos los campos.", Alert.AlertType.WARNING);
                return;
            }

            if (!banco.verificarUsuario(id, password)) {
                crearAlerta("Usuario o contraseña incorrectos.", Alert.AlertType.ERROR);
                return;
            }

            Usuario usuario = banco.buscarUsuario(id);

            sesion.setUsuario(usuario);

            crearAlerta("Inicio de sesión exitoso", Alert.AlertType.INFORMATION);
            cerrarVentana();
            irPanelCliente();

        } catch (Exception e) {
            crearAlerta("Ocurrió un error: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void crearAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Mensaje del sistema");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) IdTextField.getScene().getWindow();
        stage.close();
    }

    private void irPanelCliente() {
        navegarVentana("/panelCliente.fxml", "Banco - Panel Cliente");
    }

    private void navegarVentana(String rutaFxml, String tituloVentana) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFxml));
            Parent root = loader.load();


            PanelClienteControlador controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(tituloVentana);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            crearAlerta("No se pudo abrir la ventana: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}

