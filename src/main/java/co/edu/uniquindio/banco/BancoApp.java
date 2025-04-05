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
            banco.registrarUsuario("1001", "Juan Pérez", "Calle 123", "juan@mail.com", "pass1");
            banco.registrarUsuario("1002", "Ana Gómez", "Calle 456", "ana@mail.com", "pass2");
            banco.registrarUsuario("1003", "Luis Torres", "Carrera 12", "luis@mail.com", "pass3");
            banco.registrarUsuario("1004", "María Ríos", "Av. Siempre Viva", "maria@mail.com", "pass4");
            banco.registrarUsuario("1005", "Carlos Díaz", "Calle Luna", "carlos@mail.com", "pass5");
            banco.registrarUsuario("1006", "Laura Sánchez", "Calle Sol", "laura@mail.com", "pass6");
            banco.registrarUsuario("1007", "Pedro Castillo", "Cra. 20 #45", "pedro@mail.com", "pass7");


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
