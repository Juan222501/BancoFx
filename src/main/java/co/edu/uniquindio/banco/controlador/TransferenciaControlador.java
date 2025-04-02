package co.edu.uniquindio.banco.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TransferenciaControlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> CategoriaBox;

    @FXML
    private TextField MontoTransferirField;

    @FXML
    private TextField NumeroCuentafield;

    @FXML
    private Button TransferirButton;

    @FXML
    void initialize() {
        assert CategoriaBox != null : "fx:id=\"CategoriaBox\" was not injected: check your FXML file 'transferencia.fxml'.";
        assert MontoTransferirField != null : "fx:id=\"MontoTransferirField\" was not injected: check your FXML file 'transferencia.fxml'.";
        assert NumeroCuentafield != null : "fx:id=\"NumeroCuentafield\" was not injected: check your FXML file 'transferencia.fxml'.";
        assert TransferirButton != null : "fx:id=\"TransferirButton\" was not injected: check your FXML file 'transferencia.fxml'.";

    }

}