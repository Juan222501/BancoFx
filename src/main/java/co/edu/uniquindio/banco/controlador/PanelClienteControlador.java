package co.edu.uniquindio.banco.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import co.edu.uniquindio.banco.modelo.entidades.Banco;
import co.edu.uniquindio.banco.modelo.entidades.BilleteraVirtual;
import co.edu.uniquindio.banco.modelo.entidades.Transaccion;
import co.edu.uniquindio.banco.modelo.entidades.Usuario;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PanelClienteControlador {

    private Usuario usuario;
    private BilleteraVirtual billetera;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonCerrarSesion;

    @FXML
    private Button ButtonConsultar;

    @FXML
    private TableColumn<Transaccion,String> ColCategoria;

    @FXML
    private TableColumn<Transaccion,String> ColFecha;

    @FXML
    private TableColumn<Transaccion, String> ColTipo;

    @FXML
    private TableColumn<Transaccion, String> ColUsuario;

    @FXML
    private TableColumn<Transaccion,Float> ColValor;

    @FXML
    private Label LabelDeBievenida;

    @FXML
    private Label NumeroDeCuentaLabel;

    @FXML
    private TableView<Transaccion> TablaTransacciones;

    @FXML
    private Button buttonTransferir;

    private final Banco banco = Banco.getInstance();
    private ObservableList<Transaccion> transacciones = FXCollections.observableArrayList();



    @FXML
    void initialize() {



        ColFecha.setCellValueFactory(celda ->
                new SimpleStringProperty(celda.getValue().getFecha().toString()));

        ColTipo.setCellValueFactory(celda ->
                new SimpleStringProperty(celda.getValue().getTipoTransaccion().name()));

        ColCategoria.setCellValueFactory(celda ->
                new SimpleStringProperty(celda.getValue().getTipo().name()));

        ColUsuario.setCellValueFactory(celda -> {
            String nombre = (celda.getValue().getBilleteraOrigen() == billetera) ?
                    celda.getValue().getBilleteraDestino().getUsuario().getNombre() :
                    celda.getValue().getBilleteraOrigen().getUsuario().getNombre();
            return new SimpleStringProperty(nombre);
        });

        ColValor.setCellValueFactory(celda ->
                new SimpleFloatProperty(celda.getValue().getMonto()).asObject());

        buttonTransferir.setOnAction(this::buttonTransferir);



    }

    public void inicializarDatos(Usuario usuario) {
        this.usuario = usuario;
        this.billetera = banco.buscarBilleteraUsuario(usuario.getId());

        LabelDeBievenida.setText("¡Buen día, " + usuario.getNombre() + "!");
        NumeroDeCuentaLabel.setText("Nro. Cuenta: " + billetera.getNumero());
        actualizarDatosTabla();


    }

    public void buttonTransferir(ActionEvent actionEvent) {
        navegarVentana("/transferencia.fxml", "Banco - Panel Cliente");
        actualizarDatosTabla();
    }

    private void navegarVentana(String rutaFxml, String tituloVentana) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFxml));
            Parent root = loader.load();

            TransferenciaControlador controlador = loader.getController();
            controlador.inicializarDatos(billetera);
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

    private void crearAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Mensaje del sistema");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void actualizarDatosTabla(){
        transacciones = billetera.obtenerTransacciones()
                .stream()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        TablaTransacciones.setItems(transacciones);
    }



}
