package co.edu.uniquindio.controller;

import co.edu.uniquindio.app.AlquilaFacilApp;
import co.edu.uniquindio.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrarVehiculoController implements Initializable {

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnRegresar;

    @FXML
    private Button btnSeleccionarImagen;

    @FXML
    private TableView<Vehiculo> tableViewVehiculos;

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
    private ComboBox<MarcaVehiculo> comboBoxMarca;

    @FXML
    private ComboBox<TipoCajaVehiculo> comboBoxTipoCaja;

    @FXML
    private ImageView imageVehiculoRegistrar;

    @FXML
    private ImageView imageVehiculoSeleccionado;

    @FXML
    private Label lblSubtitulo;

    @FXML
    private Label lblTitulo;

    @FXML
    private TextField txtAlquiler;

    @FXML
    private TextField txtKilometraje;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtNumSillas;

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtReferencia;

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

        //Configuracion del idioma en el que se ven los textos de la app dependiendo el sistema operativo
        lblTitulo.setText(propiedades.getResourceBundle().getString("lblTituloRegistrarVehiculoView"));
        lblSubtitulo.setText(propiedades.getResourceBundle().getString("lblSubtituloRegistrarVehiculoView"));
        txtPlaca.setPromptText(propiedades.getResourceBundle().getString("txtPlacaRegistrarVehiculoView"));
        txtReferencia.setPromptText(propiedades.getResourceBundle().getString("txtReferenciaRegistrarVehiculoView"));
        txtModelo.setPromptText(propiedades.getResourceBundle().getString("txtModeloRegistrarVehiculoView"));
        txtKilometraje.setPromptText(propiedades.getResourceBundle().getString("txtKilometrajeRegistrarView"));
        txtAlquiler.setPromptText(propiedades.getResourceBundle().getString("txtAlquilerRegistrarVehiculoView"));
        txtNumSillas.setPromptText(propiedades.getResourceBundle().getString("txtNumSillasRegistrarVehiculoView"));
        comboBoxTipoCaja.setPromptText(propiedades.getResourceBundle().getString("comboBoxTipoCajaRegistrarVehiculoView"));
        comboBoxMarca.setPromptText(propiedades.getResourceBundle().getString("comboBoxMarcaRegistrarVehiculoView"));
        btnSeleccionarImagen.setText(propiedades.getResourceBundle().getString("btnSeleccionarImagenRegistrarVehiculoView"));
        btnCrear.setText(propiedades.getResourceBundle().getString("btnCrearRegistrarVehiculoView"));
        btnNuevo.setText(propiedades.getResourceBundle().getString("btnNuevoRegistrarVehiculoView"));
        btnActualizar.setText(propiedades.getResourceBundle().getString("btnActualizarRegistrarVehiculoView"));
        btnEliminar.setText(propiedades.getResourceBundle().getString("btnEliminarRegistrarVehiculoView"));
        columnPlaca.setText(propiedades.getResourceBundle().getString("columnPlacaRegistrarVehiculoView"));
        columnReferencia.setText(propiedades.getResourceBundle().getString("columnReferenciaRegistrarVehiculoView"));
        columnMarca.setText(propiedades.getResourceBundle().getString("columnMarcaRegitrarVehiculoView"));
        columnModelo.setText(propiedades.getResourceBundle().getString("columnModeloRegistrarVehiculoView"));
        columnKilometraje.setText(propiedades.getResourceBundle().getString("columnKilometrajeRegistrarVehiculoView"));
        columnPrecio.setText(propiedades.getResourceBundle().getString("columnPrecioRegistrarVehiculoView"));
        columnCaja.setText(propiedades.getResourceBundle().getString("columnCajaRegistrarVehiculoView"));
        columnSillas.setText(propiedades.getResourceBundle().getString("columnNumSillasRegistrarVehiculoView"));
        btnRegresar.setText(propiedades.getResourceBundle().getString("btnRegresarRegistrarVehiculoView"));

    }

    @FXML
    void actualizarVehiculo(ActionEvent event) {

    }

    @FXML
    void crearVehiculo(ActionEvent event) {

    }

    @FXML
    void eliminarVehiculo(ActionEvent event) {

    }

    @FXML
    void nuevoVehiculo(ActionEvent event) {

    }

    /**
     * Regresa a la ventana inicial
     * @param event
     */
    @FXML
    void regresar(ActionEvent event) {
        this.stage.close();
        inicioController.show();
    }

    @FXML
    void seleccionarImagen(ActionEvent event) {

    }
}
