package co.edu.uniquindio.banco.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RecargarCuenta {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    void recargar(ActionEvent event) {
        // Aquí va la lógica para recargar la cuenta
        String nombre = txtNombre.getText();
        String identificacion = txtIdentificacion.getText();

        System.out.println("Recargando cuenta para: " + nombre + " - ID: " + identificacion);
    }

    @FXML
    void initialize() {
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'recargarcuenta.fxml'.";
        assert txtIdentificacion != null : "fx:id=\"txtIdentificacion\" was not injected: check your FXML file 'recargarcuenta.fxml'.";
    }
}
