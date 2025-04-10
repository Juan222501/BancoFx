package co.edu.uniquindio.banco.controlador;


import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import co.edu.uniquindio.banco.BancoApp;
import co.edu.uniquindio.banco.modelo.entidades.Banco;
import co.edu.uniquindio.banco.modelo.entidades.BilleteraVirtual;
import co.edu.uniquindio.banco.modelo.entidades.Sesion;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RecargarCuentaControlador {

    private final Banco banco = Banco.getInstance();
    private BilleteraVirtual billeteraOrigen;
    private final Sesion sesion = Sesion.getInstancia();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField FieldMonto;

    @FXML
    private TextField FieldNumeroCuenta;

    @FXML
    void recargar(ActionEvent event) throws Exception {
        String numeroDestino = FieldNumeroCuenta.getText().trim();
        String montoTexto = FieldMonto.getText().trim();

        if (!billeteraOrigen.getNumero().equals(numeroDestino)) {
            throw new Exception("El numero de billetera no coincide.");
        }

        if (numeroDestino.isEmpty() || montoTexto.isEmpty()) {
            throw new Exception("Por favor completa todos los campos.");
        }
        float monto;
        try {
            monto = Float.parseFloat(montoTexto);
        } catch (NumberFormatException e) {
            throw new Exception("El monto debe ser un número válido.");
        }

        banco.recargarBilletera(billeteraOrigen.getNumero(), monto);

        mostrarAlerta("Éxito", "Transferencia realizada correctamente.");
        limpiarCampos();



    }

        @FXML
    void initialize() {

        inicializarDatos();
        assert FieldMonto != null : "fx:id=\"FieldIdentificacion\" was not injected: check your FXML file 'recargarcuenta.fxml'.";
        assert FieldNumeroCuenta != null : "fx:id=\"FieldNombre\" was not injected: check your FXML file 'recargarcuenta.fxml'.";
            Platform.runLater(() -> {
                Stage stage = (Stage) FieldMonto.getScene().getWindow();
                stage.setOnCloseRequest(event -> irPanelCliente());
            });

    }

    public void inicializarDatos() {
        this.billeteraOrigen = banco.buscarBilleteraUsuario(sesion.getUsuario().getId());
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(BancoApp.class.getResourceAsStream("/img/S.png"))));
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();

    }

    /**
     * Muestra una alerta con un tipo específico.
     *
     * @param mensaje Contenido del mensaje
     * @param tipo Tipo de alerta (ERROR, WARNING, etc.)
     */
    public void crearAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        FieldMonto.clear();
        FieldNumeroCuenta.clear();
    }

    private void irPanelCliente() {
        navegarVentana("/panelCliente.fxml", "Banco - Panel Cliente");
    }
    private void navegarVentana(String rutaFxml, String tituloVentana) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFxml));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.getIcons().add(new Image(Objects.requireNonNull(BancoApp.class.getResourceAsStream("/img/S.png"))));
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
