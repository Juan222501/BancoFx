package co.edu.uniquindio.banco;

import co.edu.uniquindio.banco.modelo.entidades.Banco;
import co.edu.uniquindio.banco.modelo.entidades.BilleteraVirtual;
import co.edu.uniquindio.banco.modelo.enums.Categoria;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Clase principal de la aplicación BancoApp.
 * Se encarga de iniciar la interfaz gráfica y cargar algunos usuarios de prueba.
 * Extiende la clase Application de JavaFX.
 */
public class BancoApp extends Application {

    /**
     * Instancia del banco utilizada para manejar la lógica del sistema.
     */
    private final Banco banco = Banco.getInstance();

    /**
     * Carga usuarios de prueba, crea sus billeteras, realiza una recarga y una transferencia.
     * También imprime los saldos en consola para verificar que la operación fue exitosa.
     */
    private void cargarUsuarios() {
        try {
            banco.registrarUsuario("2112", "Santiago Torres", "Calle 07327", "shant@mail.com", "pass21");
            banco.registrarUsuario("1221", "Daiana Ramirez", "Calle 03727", "Amore@mail.com", "pass12");

            BilleteraVirtual billeteraShant = banco.buscarBilleteraUsuario("2112");
            BilleteraVirtual billeteraMore = banco.buscarBilleteraUsuario("1221");

            String numeroShant = billeteraShant.getNumero();
            String numeroMore = billeteraMore.getNumero();

            banco.recargarBilletera(numeroShant, 5000);
            banco.realizarTransferencia(numeroShant, numeroMore, 2127, Categoria.RECARGA);

            System.out.println("Saldo Santiago: $" + billeteraShant.consultarSaldo());
            System.out.println("Saldo Daiana: $" + billeteraMore.consultarSaldo());
            System.out.println(numeroShant);
            System.out.println(numeroMore);

        } catch (Exception e) {
            System.out.println("Error al registrar usuarios iniciales: " + e.getMessage());
        }
    }

    /**
     * Método que se ejecuta al iniciar la aplicación JavaFX.
     * Carga los datos iniciales y muestra la escena principal definida en 'inicio.fxml'.
     *
     * @param stage escenario principal de la aplicación.
     * @throws Exception si ocurre un error al cargar el FXML.
     */
    @Override
    public void start(Stage stage) throws Exception {
        cargarUsuarios();

        FXMLLoader loader = new FXMLLoader(BancoApp.class.getResource("/inicio.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        stage.getIcons().add(new Image(Objects.requireNonNull(BancoApp.class.getResourceAsStream("/img/S.png"))));
        stage.setScene(scene);
        stage.setTitle("Banco");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Método main que lanza la aplicación JavaFX.
     *
     * @param args argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch(BancoApp.class, args);
    }
}
