package co.edu.uniquindio.banco.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PanelClienteControlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonCerrarSesion;

    @FXML
    private Button ButtonConsultar;

    @FXML
    private TableColumn<?, ?> ColCategoria;

    @FXML
    private TableColumn<?, ?> ColFecha;

    @FXML
    private TableColumn<?, ?> ColTipo;

    @FXML
    private TableColumn<?, ?> ColUsuario;

    @FXML
    private TableColumn<?, ?> ColValor;

    @FXML
    private Label LabelDeBievenida;

    @FXML
    private Label NumeroDeCuentaLabel;

    @FXML
    private TableView<?> TablaTransacciones;

    @FXML
    private Button buttonTransferir;

    @FXML
    void initialize() {
        assert ButtonCerrarSesion != null : "fx:id=\"ButtonCerrarSesion\" was not injected: check your FXML file 'panelCliente.fxml'.";
        assert ButtonConsultar != null : "fx:id=\"ButtonConsultar\" was not injected: check your FXML file 'panelCliente.fxml'.";
        assert ColCategoria != null : "fx:id=\"ColCategoria\" was not injected: check your FXML file 'panelCliente.fxml'.";
        assert ColFecha != null : "fx:id=\"ColFecha\" was not injected: check your FXML file 'panelCliente.fxml'.";
        assert ColTipo != null : "fx:id=\"ColTipo\" was not injected: check your FXML file 'panelCliente.fxml'.";
        assert ColUsuario != null : "fx:id=\"ColUsuario\" was not injected: check your FXML file 'panelCliente.fxml'.";
        assert ColValor != null : "fx:id=\"ColValor\" was not injected: check your FXML file 'panelCliente.fxml'.";
        assert LabelDeBievenida != null : "fx:id=\"LabelDeBievenida\" was not injected: check your FXML file 'panelCliente.fxml'.";
        assert NumeroDeCuentaLabel != null : "fx:id=\"NumeroDeCuentaLabel\" was not injected: check your FXML file 'panelCliente.fxml'.";
        assert TablaTransacciones != null : "fx:id=\"TablaTransacciones\" was not injected: check your FXML file 'panelCliente.fxml'.";
        assert buttonTransferir != null : "fx:id=\"buttonTransferir\" was not injected: check your FXML file 'panelCliente.fxml'.";

    }

}
