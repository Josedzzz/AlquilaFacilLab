package co.edu.uniquindio.controller;

import co.edu.uniquindio.app.AlquilaFacilApp;
import co.edu.uniquindio.exceptions.AtributoNegativoException;
import co.edu.uniquindio.exceptions.AtributosVaciosException;
import co.edu.uniquindio.exceptions.VehiculoNoRegistradoException;
import co.edu.uniquindio.exceptions.VehiculoYaExistenteException;
import co.edu.uniquindio.model.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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

    @FXML
    private TextField txtRutaImgen;

    //Variables auxiliares
    private AlquilaFacilApp alquilaFacilApp;
    private Stage stage;
    private InicioController inicioController;
    private ObservableList<Vehiculo> listadoVehiculos = FXCollections.observableArrayList();
    private Vehiculo vehiculoSeleccion;

    //Uso del singleton
    private final Empresa empresa = Empresa.getInstance();
    private final Propiedades propiedades = Propiedades.getInstance();

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
        tableViewVehiculos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                vehiculoSeleccion = newSelection;
                vehiculoSeleccion = tableViewVehiculos.getSelectionModel().getSelectedItem();
                txtPlaca.setDisable(true);
                txtReferencia.setDisable(true);
                txtModelo.setDisable(true);
                txtNumSillas.setDisable(true);
                comboBoxMarca.setDisable(true);
                llenarCamposVehiculo(vehiculoSeleccion);
            }
        });

        //Manejo de comboBox del vehiculo
        this.comboBoxMarca.getItems().addAll(MarcaVehiculo.values());
        this.comboBoxTipoCaja.getItems().addAll(TipoCajaVehiculo.values());

        //Descativo el campo de ruta de la imagen
        txtRutaImgen.setDisable(true);
    }

    public void setAlquilaFacilApp(AlquilaFacilApp alquilaFacilApp) {
        this.alquilaFacilApp = alquilaFacilApp;
        //Lista de vehiculos a mostrar
        tableViewVehiculos.getItems().clear();
        tableViewVehiculos.setItems(getListaVehiculos());
    }

    /**
     * Obtengo la lista de vehiculos de la empresa
     * @return
     */
    private ObservableList<Vehiculo> getListaVehiculos() {
        listadoVehiculos.addAll(empresa.getListaVehiculos());
        return listadoVehiculos;
    }

    public void init(Stage stage, InicioController inicioController) {
        this.stage = stage;
        this.inicioController = inicioController;
    }

    /**
     * Muestra la informacion de un vehiculo seleccionado
     * @param vehiculoSeleccion
     */
    private void llenarCamposVehiculo(Vehiculo vehiculoSeleccion) {
        txtPlaca.setText(vehiculoSeleccion.getPlaca());
        txtReferencia.setText(vehiculoSeleccion.getReferencia());
        txtModelo.setText(vehiculoSeleccion.getModelo());
        txtKilometraje.setText(Double.toString(vehiculoSeleccion.getKilometraje()));
        txtAlquiler.setText(Double.toString(vehiculoSeleccion.getPrecioAlquiler()));
        txtNumSillas.setText(Integer.toString(vehiculoSeleccion.getNumSillas()));
        txtRutaImgen.setText(vehiculoSeleccion.getImagenVehiculo());
        comboBoxTipoCaja.getSelectionModel().select(vehiculoSeleccion.getTipoCajaVehiculo());
        comboBoxMarca.getSelectionModel().select(vehiculoSeleccion.getMarcaVehiculo());
        //Cargo la imagen del vehiculo
        String rutaImagen = vehiculoSeleccion.getImagenVehiculo();
        Image imagenVehiculoSeleccion = new Image("file:" + rutaImagen);
        imageVehiculoSeleccionado.setImage(imagenVehiculoSeleccion);
        imageVehiculoRegistrar.setImage(imagenVehiculoSeleccion);
    }

    /**
     * Limpia todos los campos de un vehiculo
     */
    private void limpiarCamposVehiculo() {
        txtPlaca.clear();
        txtReferencia.clear();
        txtModelo.clear();
        txtKilometraje.clear();
        txtAlquiler.clear();
        txtNumSillas.clear();
        txtRutaImgen.clear();
        comboBoxTipoCaja.setValue(null);
        comboBoxMarca.setValue(null);
        imageVehiculoSeleccionado.setImage(null);
        imageVehiculoRegistrar.setImage(null);
        txtPlaca.setDisable(false);
        txtReferencia.setDisable(false);
        txtModelo.setDisable(false);
        txtNumSillas.setDisable(false);
        comboBoxMarca.setDisable(false);
    }

    /**
     * Limpica los campos de un vehiculo cuando este se selcciona
     * @param event
     */
    @FXML
    void nuevoVehiculo(ActionEvent event) {
        limpiarCamposVehiculo();
    }

    /**
     * Actualiza los datos kilometraje, precio alquiler y tipo de caja de un vehiculo
     * @param event
     */
    @FXML
    void actualizarVehiculo(ActionEvent event) {
        try {
            empresa.actualizarVehiculo(
                    txtPlaca.getText(),
                    Double.parseDouble(txtKilometraje.getText()),
                    Double.parseDouble(txtAlquiler.getText()),
                    comboBoxTipoCaja.getValue()
            );
            limpiarCamposVehiculo();
            tableViewVehiculos.getItems().clear();
            tableViewVehiculos.setItems(getListaVehiculos());
            mostrarMensaje("Notificación AlquilaFacil", "Información valida", "El vehiculo ha sido actualizado de manera correcta", Alert.AlertType.INFORMATION);
        } catch (VehiculoNoRegistradoException e) {
            mostrarMensaje("Notificación AlquilaFacil", "Información invalida", e.getMessage(), Alert.AlertType.ERROR);
        } catch (AtributoNegativoException e) {
            mostrarMensaje("Notificación AlquilaFacil", "Información invalida", e.getMessage(), Alert.AlertType.ERROR);
        } catch (AtributosVaciosException e) {
            mostrarMensaje("Notificación AlquilaFacil", "Información invalida", e.getMessage(), Alert.AlertType.ERROR);
        } catch (NumberFormatException e) {
            mostrarMensaje("Notificación ALquilaFacil", "Información invalida", "Asegurese de que en el apartado kilometraje, alquiler o sillas solo hayan numeros", Alert.AlertType.ERROR);
        }
    }

    /**
     * Crea un vehiculo y verifica que toda la información sea valida
     * @param event
     */
    @FXML
    void crearVehiculo(ActionEvent event) {
        try {
            empresa.crearVehiculo(
                    txtPlaca.getText(),
                    txtReferencia.getText(),
                    comboBoxMarca.getValue(),
                    txtModelo.getText(),
                    txtRutaImgen.getText(),
                    Double.parseDouble(txtKilometraje.getText()),
                    Double.parseDouble(txtAlquiler.getText()),
                    comboBoxTipoCaja.getValue(),
                    Integer.parseInt(txtNumSillas.getText())
            );
            limpiarCamposVehiculo();
            tableViewVehiculos.getItems().clear();
            tableViewVehiculos.setItems(getListaVehiculos());
            mostrarMensaje("Notificación AlquilaFacil", "Información valida", "El vehiculo ha sido registrado de manera correcta", Alert.AlertType.INFORMATION);
        } catch (VehiculoYaExistenteException e) {
            mostrarMensaje("Notificación AlquilaFacil", "Información invalida", e.getMessage(), Alert.AlertType.ERROR);
        } catch (AtributoNegativoException e) {
            mostrarMensaje("Notificación AlquilaFacil", "Información invalida", e.getMessage(), Alert.AlertType.ERROR);
        } catch (AtributosVaciosException e) {
            mostrarMensaje("Notificación AlquilaFacil", "Información invalida", e.getMessage(), Alert.AlertType.ERROR);
        } catch (NumberFormatException e) {
            mostrarMensaje("Notificación ALquilaFacil", "Información invalida", "Asegurese de que en el apartado kilometraje, alquiler o sillas solo hayan numeros", Alert.AlertType.ERROR);
        }
    }

    /**
     * Elimina un vehiculo seleccionado de la tabla
     * @param event
     */
    @FXML
    void eliminarVehiculo(ActionEvent event) {
        try {
            if (vehiculoSeleccion != null) {
                String placa = vehiculoSeleccion.getPlaca();
                empresa.eliminarVehiculo(placa);
                limpiarCamposVehiculo();
                tableViewVehiculos.getItems().clear();
                tableViewVehiculos.setItems(getListaVehiculos());
                mostrarMensaje("Notificación AlquilaFacil", "Información valida", "El vehiculo ha sido eliminado de manera correcta", Alert.AlertType.INFORMATION);
            } else {
                mostrarMensaje("Notificación AlquilaFacil", "Información invalida", "Por favor seleccione un vehículo en la tabla", Alert.AlertType.INFORMATION);
            }
        } catch (VehiculoNoRegistradoException e) {
            mostrarMensaje("Notificación AlquilaFacil", "Información invalida", e.getMessage(), Alert.AlertType.ERROR);
        }
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

    /**
     * Selecciona una imagen y copia la ruta de esta en el texfield
     * @param event
     */
    @FXML
    void seleccionarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg"));
        Stage stage = new Stage();
        File archivoSeleccionado = fileChooser.showOpenDialog(stage);
        if (archivoSeleccionado != null) {
            String rutaImagen = archivoSeleccionado.getAbsolutePath();
            Image imagenVehiculo = new Image("file:" + rutaImagen);
            imageVehiculoRegistrar.setImage(imagenVehiculo);
            txtRutaImgen.setText(rutaImagen);
        }
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
