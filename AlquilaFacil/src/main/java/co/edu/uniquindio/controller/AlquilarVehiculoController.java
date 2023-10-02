package co.edu.uniquindio.controller;

import co.edu.uniquindio.app.AlquilaFacilApp;
import co.edu.uniquindio.model.Empresa;
import co.edu.uniquindio.model.Propiedades;
import co.edu.uniquindio.model.Vehiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AlquilarVehiculoController implements Initializable {
    @FXML
    private ImageView ImageVehiculo;

    @FXML
    private Button btnGenerarFactura;

    @FXML
    private Button btnRegresar;

    @FXML
    private Button btnVehiculosDisponibles;

    @FXML
    private TableView<Vehiculo> tableViewVehiculosDisponibles;

    @FXML
    private TableColumn<Vehiculo, String> columnCaja;

    @FXML
    private TableColumn<Vehiculo, String> columnKilometraje;

    @FXML
    private TableColumn<Vehiculo, String> columnMarca;

    @FXML
    private TableColumn<Vehiculo, String> columnModelo;

    @FXML
    private TableColumn<Vehiculo, String> columnPlaca;

    @FXML
    private TableColumn<Vehiculo, String> columnPrecio;

    @FXML
    private TableColumn<Vehiculo, String> columnReferencia;

    @FXML
    private TableColumn<Vehiculo, String> columnSillas;

    @FXML
    private DatePicker dateFechaFinal;

    @FXML
    private DatePicker dateFechaInicial;

    @FXML
    private Label lblCedulaCliente;

    @FXML
    private Label lblFechaFinal;

    @FXML
    private Label lblFechaInicial;

    @FXML
    private Label lblSubtitulo;

    @FXML
    private Label lblTitulo;

    @FXML
    private TextField txtCedulaCliente;

    //Variables auxiliares
    private AlquilaFacilApp alquilaFacilApp;
    private Stage stage;
    private InicioController inicioController;

    //Uso del singleton
    private final Empresa empresa = Empresa.getInstance();
    private final Propiedades propiedades = Propiedades.getInstance();

    public void setAlquilaFacilApp(AlquilaFacilApp alquilaFacilApp) {
        this.alquilaFacilApp = alquilaFacilApp;
    }

    public void init(Stage stage, InicioController inicioController) {
        this.stage = stage;
        this.inicioController = inicioController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void generarFactura(ActionEvent event) {

    }

    /**
     * Regresa a la ventana de inicio
     * @param event
     */
    @FXML
    void regresar(ActionEvent event) {
        this.stage.close();
        inicioController.show();
    }

    @FXML
    void verVehiculosDisponibles(ActionEvent event) {

    }
}
