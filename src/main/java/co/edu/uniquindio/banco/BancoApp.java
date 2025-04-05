package co.edu.uniquindio.banco;

import co.edu.uniquindio.banco.modelo.entidades.Banco;
import co.edu.uniquindio.banco.modelo.entidades.BilleteraVirtual;
import co.edu.uniquindio.banco.modelo.entidades.Transaccion;
import co.edu.uniquindio.banco.modelo.entidades.Usuario;
import co.edu.uniquindio.banco.modelo.enums.Categoria;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Clase que representa la aplicación de un banco y que se encarga
 * de cargar la ventana principal de la aplicación.
 * @author caflorezvi
 */
public class BancoApp extends Application {
    private final Banco banco = Banco.getInstance();


    private void cargarUsuarios() {
        try {
            banco.registrarUsuario("2112", "Santiago Torres", "Calle 07327", "shant@mail.com", "pass21");
            banco.registrarUsuario("1221", "Daiana Ramirez", "Calle 03727", "more@mail.com", "pass12");

            BilleteraVirtual billeteraShant = banco.buscarBilleteraUsuario("2112");
            BilleteraVirtual billeteraMore = banco.buscarBilleteraUsuario("1221");

            String numeroShant = billeteraShant.getNumero();
            String numeroMore = billeteraMore.getNumero();

            // 3. Recargar (depositar) en la billetera de Shant
            banco.recargarBilletera(numeroShant, 50);

            // 4. Realizar transferencia de Santiago a Daiana
            banco.realizarTransferencia(numeroShant, numeroMore, 30, Categoria.RECARGA);

            // 5. Mostrar saldos después de la operación
            System.out.println("Saldo Santiago: $" + billeteraShant.consultarSaldo());
            System.out.println("Saldo Daiana: $" + billeteraMore.consultarSaldo());
            System.out.println(numeroShant);
            System.out.println(numeroMore);


        } catch (Exception e) {
            System.out.println("Error al registrar usuarios iniciales: " + e.getMessage());
        }
    }




    @Override
    public void start(Stage stage) throws Exception {
        cargarUsuarios();


        FXMLLoader loader = new FXMLLoader(BancoApp.class.getResource("/inicio.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Banco");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(BancoApp.class, args);
    }
}
