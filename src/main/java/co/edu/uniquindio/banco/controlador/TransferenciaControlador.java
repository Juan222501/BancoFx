package co.edu.uniquindio.banco.controlador;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import co.edu.uniquindio.banco.modelo.entidades.Banco;
import co.edu.uniquindio.banco.modelo.entidades.BilleteraVirtual;
import co.edu.uniquindio.banco.modelo.entidades.Transaccion;
import co.edu.uniquindio.banco.modelo.enums.Categoria;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TransferenciaControlador {

    private BilleteraVirtual billeteraOrigen;
    private final Banco banco = Banco.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Categoria> CategoriaBox;

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

        CategoriaBox.getItems().addAll(Categoria.values());


        TransferirButton.setOnAction(e -> {
            try {
                realizarTransferencia();
            } catch (Exception ex) {
                mostrarAlerta("Error", ex.getMessage());
            }
        });
    }

    public void inicializarDatos(BilleteraVirtual billeteraOrigen) {
        this.billeteraOrigen = billeteraOrigen;
    }

    private void realizarTransferencia() throws Exception {
        String numeroCuentaDestino = NumeroCuentafield.getText().trim();
        String montoTexto = MontoTransferirField.getText().trim();
        Categoria categoriaSeleccionada = CategoriaBox.getValue();

        if (numeroCuentaDestino.isEmpty() || montoTexto.isEmpty() || categoriaSeleccionada == null) {
            throw new Exception("Por favor completa todos los campos.");
        }

        float monto;
        try {
            monto = Float.parseFloat(montoTexto);
        } catch (NumberFormatException e) {
            throw new Exception("El monto debe ser un número válido.");
        }


        BilleteraVirtual billeteraDestino = banco.buscarBilletera(numeroCuentaDestino);

        if (billeteraOrigen.getNumero().equals(billeteraDestino.getNumero())) {
            throw new Exception("No puedes transferirte a tu misma billetera.");
        }

        if (!billeteraOrigen.tieneSaldo(monto)) {
            throw new Exception("Saldo insuficiente para realizar la transferencia.");
        }



        banco.realizarTransferencia(billeteraOrigen.getNumero(), numeroCuentaDestino, monto, categoriaSeleccionada);

        mostrarAlerta("Éxito", "Transferencia realizada correctamente.");
        limpiarCampos();
    }

    private void limpiarCampos() {
        MontoTransferirField.clear();
        NumeroCuentafield.clear();
        CategoriaBox.getSelectionModel().clearSelection();
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}