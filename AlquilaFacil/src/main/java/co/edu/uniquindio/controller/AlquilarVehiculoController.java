package co.edu.uniquindio.controller;

import co.edu.uniquindio.app.AlquilaFacilApp;
import co.edu.uniquindio.exceptions.AtributosVaciosException;
import co.edu.uniquindio.exceptions.ClienteNoRegistradoException;
import co.edu.uniquindio.exceptions.FechaInvalidaException;
import co.edu.uniquindio.model.Empresa;
import co.edu.uniquindio.utilities.Propiedades;
import co.edu.uniquindio.model.Registro;
import co.edu.uniquindio.model.Vehiculo;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
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
    private ObservableList<Vehiculo> listadoVehiculos = FXCollections.observableArrayList();
    private Vehiculo vehiculoSeleccion;
    private LocalDate fechaInicialFiltrar;
    private LocalDate fechaFinalFiltrar;

    //Uso del singleton
    private final Empresa empresa = Empresa.getInstance();
    private final Propiedades propiedades = Propiedades.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Configuracion del idioma en el que se ven los textos de la app dependiendo el sistema operativo
        lblTitulo.setText(propiedades.getResourceBundle().getString("lblTituloAlquilarVehiculoView"));
        lblSubtitulo.setText(propiedades.getResourceBundle().getString("lblSubtituloAlquilarVehiculoView"));
        btnRegresar.setText(propiedades.getResourceBundle().getString("btnRegresarAlquilarVehiculoView"));
        lblFechaInicial.setText(propiedades.getResourceBundle().getString("lblFechaInicialAlquilarVehiculoView"));
        lblFechaFinal.setText(propiedades.getResourceBundle().getString("lblFechaFinalAlquilarVehiculoView"));
        lblCedulaCliente.setText(propiedades.getResourceBundle().getString("lblCedulaClienteAlquilarVehiculoView"));
        txtCedulaCliente.setPromptText(propiedades.getResourceBundle().getString("txtCedulaClienteAlquilarVehiculoView"));
        btnGenerarFactura.setText(propiedades.getResourceBundle().getString("btnGenerarFacturaAlquilarVehiculoView"));
        btnVehiculosDisponibles.setText(propiedades.getResourceBundle().getString("btnVehiculosDisponiblesAlquilarVehiculoView"));
        columnPlaca.setText(propiedades.getResourceBundle().getString("columnPlacaAlquilarVehiculoView"));
        columnReferencia.setText(propiedades.getResourceBundle().getString("columnReferenciaAlquilarVehiculoView"));
        columnMarca.setText(propiedades.getResourceBundle().getString("columnMarcaAlquilarVehiculoView"));
        columnModelo.setText(propiedades.getResourceBundle().getString("columnModeloAlquilarVehiculoView"));
        columnKilometraje.setText(propiedades.getResourceBundle().getString("columnKilometrajeAlquilarVehiculoView"));
        columnPrecio.setText(propiedades.getResourceBundle().getString("columnPrecioAlquilarVehiculoView"));
        columnCaja.setText(propiedades.getResourceBundle().getString("columnCajaAlquilarVehiculoView"));
        columnSillas.setText(propiedades.getResourceBundle().getString("columnSillasAlquilarVehiculoView"));
        //Datos de la tableView de vehiculos
        this.columnPlaca.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getPlaca()));
        this.columnReferencia.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getReferencia()));
        this.columnMarca.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getMarcaVehiculo().toString()));
        this.columnModelo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getModelo()));
        this.columnKilometraje.setCellValueFactory(e -> {
            double kilometraje = e.getValue().getKilometraje();
            String kilometrajeString = String.valueOf(kilometraje);
            return new ReadOnlyStringWrapper(kilometrajeString);
        });
        this.columnPrecio.setCellValueFactory(e -> {
            double precio = e.getValue().getPrecioAlquiler();
            String precioString = String.valueOf(precio);
            return new ReadOnlyStringWrapper(precioString);
        });
        this.columnCaja.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getTipoCajaVehiculo().toString()));
        this.columnSillas.setCellValueFactory( e -> {
            int sillas = e.getValue().getNumSillas();
            String sillasString = String.valueOf(sillas);
            return new ReadOnlyStringWrapper(sillasString);
        });
        //Selecciono vehiculos de la tabla
        tableViewVehiculosDisponibles.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                vehiculoSeleccion = newSelection;
                vehiculoSeleccion = tableViewVehiculosDisponibles.getSelectionModel().getSelectedItem();
                String rutaImagen = vehiculoSeleccion.getImagenVehiculo();
                Image imagenVehiculoSeleccion = new Image("file:" + rutaImagen);
                ImageVehiculo.setImage(imagenVehiculoSeleccion);
            }
        });
        //Menejo de la fecha inicial y de la fecha final para los filtros
        dateFechaInicial.setOnAction(event -> {
            fechaInicialFiltrar = dateFechaInicial.getValue();
        });
        dateFechaFinal.setOnAction(event -> {
            fechaFinalFiltrar = dateFechaFinal.getValue();
        });
    }

    public void setAlquilaFacilApp(AlquilaFacilApp alquilaFacilApp) {
        this.alquilaFacilApp = alquilaFacilApp;
    }

    public void init(Stage stage, InicioController inicioController) {
        this.stage = stage;
        this.inicioController = inicioController;
    }

    /**
     * Limpia los campos de la interfaz
     */
    public void limpiarCampos() {
        dateFechaInicial.setValue(null);
        dateFechaFinal.setValue(null);
        txtCedulaCliente.setText("");
        ImageVehiculo.setImage(null);
    }

    /**
     * Genera un registro
     * @param event
     */
    @FXML
    void generarFactura(ActionEvent event) {
        try {
            Registro registro = empresa.crearRegistro(
                    txtCedulaCliente.getText(),
                    vehiculoSeleccion,
                    fechaInicialFiltrar,
                    fechaFinalFiltrar
            );
            mostrarMensaje(propiedades.getResourceBundle().getString("mostrarMensajeTitulo"), propiedades.getResourceBundle().getString("mostrarMensajeHeaderAfirmativo"), propiedades.getResourceBundle().getString("generarFacturaAlquilar") + " " + registro.getPrecioFactura(), Alert.AlertType.INFORMATION);
            tableViewVehiculosDisponibles.getItems().clear();
            limpiarCampos();
        } catch (ClienteNoRegistradoException e) {
            mostrarMensaje(propiedades.getResourceBundle().getString("mostrarMensajeTitulo"), propiedades.getResourceBundle().getString("mostrarMensajeHeaderNegativo"), e.getMessage(), Alert.AlertType.ERROR);
        } catch (AtributosVaciosException e) {
            mostrarMensaje(propiedades.getResourceBundle().getString("mostrarMensajeTitulo"), propiedades.getResourceBundle().getString("mostrarMensajeHeaderNegativo"), e.getMessage(), Alert.AlertType.ERROR);
        } catch (FechaInvalidaException e) {
            mostrarMensaje(propiedades.getResourceBundle().getString("mostrarMensajeTitulo"), propiedades.getResourceBundle().getString("mostrarMensajeHeaderNegativo"), e.getMessage(), Alert.AlertType.ERROR);
        }
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

    /**
     * Me muestra los vehiculos disponibles dada una fecha
     * @param event
     */
    @FXML
    void verVehiculosDisponibles(ActionEvent event) {
        try {
            empresa.validarFechas(fechaInicialFiltrar, fechaFinalFiltrar);
            tableViewVehiculosDisponibles.getItems().clear();
            tableViewVehiculosDisponibles.setItems(getListaVehiculosDisponibles(fechaInicialFiltrar, fechaFinalFiltrar));
            mostrarMensaje(propiedades.getResourceBundle().getString("mostrarMensajeTitulo"), "Información valida", "El filtro de vehiculos se ha realizado de forma correcta", Alert.AlertType.INFORMATION);
        } catch (AtributosVaciosException e) {
            mostrarMensaje(propiedades.getResourceBundle().getString("mostrarMensajeTitulo"), propiedades.getResourceBundle().getString("mostrarMensajeHeaderNegativo"), e.getMessage(), Alert.AlertType.ERROR);
        } catch (FechaInvalidaException e) {
            mostrarMensaje(propiedades.getResourceBundle().getString("mostrarMensajeTitulo"), propiedades.getResourceBundle().getString("mostrarMensajeHeaderNegativo"), e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    /**
     * Obtiene la lista de vehiculos disponibles
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    private ObservableList<Vehiculo> getListaVehiculosDisponibles(LocalDate fechaInicial, LocalDate fechaFinal) {
        listadoVehiculos.addAll(empresa.obtenerVehiculosDisponibles(fechaInicial, fechaFinal));
        return listadoVehiculos;
    }

    /**
     * Muestra un mensaje dependiendo con el tipo de alerta seleccionado
     * @param title
     * @param header
     * @param content
     * @param alertType
     */
    public void mostrarMensaje(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
