package co.edu.uniquindio.controller;

import co.edu.uniquindio.app.AlquilaFacilApp;
import co.edu.uniquindio.exceptions.AtributosVaciosException;
import co.edu.uniquindio.exceptions.CLienteYaExistenteException;
import co.edu.uniquindio.exceptions.ClienteNoRegistradoException;
import co.edu.uniquindio.model.Cliente;
import co.edu.uniquindio.model.Empresa;
import co.edu.uniquindio.model.Propiedades;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrarClienteController implements Initializable {
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
    private TableView<Cliente> tableViewClientes;

    @FXML
    private TableColumn<Cliente, String> columnCedula;

    @FXML
    private TableColumn<Cliente, String> columnCiudad;

    @FXML
    private TableColumn<Cliente, String> columnDireccion;

    @FXML
    private TableColumn<Cliente, String> columnEmail;

    @FXML
    private TableColumn<Cliente, String> columnNombre;

    @FXML
    private TableColumn<Cliente, String> columnTelefono;

    @FXML
    private Label lblSubtitulo;

    @FXML
    private Label lblTitutlo;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    //Variables auxiliares
    private AlquilaFacilApp alquilaFacilApp;
    private Stage stage;
    private Empresa application;
    private InicioController inicioController;
    private ObservableList<Cliente> listadoClientes = FXCollections.observableArrayList();
    private Cliente clienteSeleccion;

    //Uso del singleton
    private final Empresa empresa = Empresa.getInstance();
    private final Propiedades propiedades = Propiedades.getInstance();

    public void setAlquilaFacilApp(AlquilaFacilApp alquilaFacilApp) {
        this.alquilaFacilApp = alquilaFacilApp;
        //lista de clientes a mostrar
        tableViewClientes.getItems().clear();
        tableViewClientes.setItems(getListaClientes());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Configuracion del idioma en el que se ven los textos de la app dependiendo el sistema operativo
        lblTitutlo.setText(propiedades.getResourceBundle().getString("lblTituloRegistrarClienteView"));
        btnRegresar.setText(propiedades.getResourceBundle().getString("btnRegresarRegistrarClienteView"));
        txtCedula.setPromptText(propiedades.getResourceBundle().getString("txtCedulaRegistrarClienteView"));
        txtEmail.setPromptText(propiedades.getResourceBundle().getString("txtEmailRegistrarClienteView"));
        txtNombre.setPromptText(propiedades.getResourceBundle().getString("txtNombreRegistrarClienteView"));
        txtCiudad.setPromptText(propiedades.getResourceBundle().getString("txtCiudadRegistrarClienteView"));
        txtTelefono.setPromptText(propiedades.getResourceBundle().getString("txtTelefonoRegistrarClienteView"));
        txtDireccion.setPromptText(propiedades.getResourceBundle().getString("txtDireccionRegistrarClienteView"));
        btnCrear.setText(propiedades.getResourceBundle().getString("btnCrearRegistrarClienteView"));
        btnNuevo.setText(propiedades.getResourceBundle().getString("btnNuevoRegistrarClienteView"));
        btnActualizar.setText(propiedades.getResourceBundle().getString("btnActualizarRegistrarClienteView"));
        lblSubtitulo.setText(propiedades.getResourceBundle().getString("lblSubtituloRegistrarClienteView"));
        columnCedula.setText(propiedades.getResourceBundle().getString("columnCedulaRegistrarClienteView"));
        columnNombre.setText(propiedades.getResourceBundle().getString("columnNombreRegistrarClienteView"));
        columnTelefono.setText(propiedades.getResourceBundle().getString("columnTelefonoRegistrarClienteView"));
        columnEmail.setText(propiedades.getResourceBundle().getString("columnEmailRegistrarClienteView"));
        columnCiudad.setText(propiedades.getResourceBundle().getString("columnCiudadRegistrarClienteView"));
        columnDireccion.setText(propiedades.getResourceBundle().getString("columnDireccionRegistrarClienteView"));
        btnEliminar.setText(propiedades.getResourceBundle().getString("btnEliminarRegistrarClienteView"));

        //Datos de la tableView de cliente
        this.columnCedula.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCedula()));
        this.columnNombre.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombre()));
        this.columnTelefono.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getTelefono()));
        this.columnEmail.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getEmail()));
        this.columnCiudad.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCiudad()));
        this.columnDireccion.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getDireccion()));

        //Seleccion de cliente en la tabla
        tableViewClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                clienteSeleccion = newSelection;
                clienteSeleccion = tableViewClientes.getSelectionModel().getSelectedItem();
                txtCedula.setDisable(true);
                llenarCamposCliente(clienteSeleccion);
            }
        });
    }

    /**
     * Obtengo la lista de clientes
     * @return
     */
    private ObservableList<Cliente> getListaClientes() {
        listadoClientes.clear();
        listadoClientes.addAll(Empresa.getInstance().getListaClientes());
        return listadoClientes;
    }

    public void init(Stage stage, InicioController inicioController) {
        this.stage = stage;
        this.inicioController = inicioController;
    }

    /**
     * Muestra un mensaje por pantalla
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

    /**
     * Llena los campos de los textFields segun lo seleccionado en la tableView
     * @param clienteSeleccion
     */
    private void llenarCamposCliente(Cliente clienteSeleccion) {
        txtCedula.setText(clienteSeleccion.getCedula());
        txtNombre.setText(clienteSeleccion.getNombre());
        txtTelefono.setText(clienteSeleccion.getTelefono());
        txtEmail.setText(clienteSeleccion.getEmail());
        txtCiudad.setText(clienteSeleccion.getCiudad());
        txtDireccion.setText(clienteSeleccion.getDireccion());
    }

    /**
     * Limpia los textFields
     * @param cedula
     * @param nombre
     * @param telefono
     * @param email
     * @param ciudad
     * @param direccion
     */
    public void limpiarCamposCliente(){
        txtCedula.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtEmail.clear();
        txtCiudad.clear();
        txtDireccion.clear();
    }

    /**
     * Actualiza los datos del cliente
     * @param cedula
     * @param nombre
     * @param telefono
     * @param email
     * @param ciudad
     * @param direccion
     * @throws ClienteNoRegistradoException
     * @throws AtributosVaciosException
     */
    @FXML
    void actualizarCliente(ActionEvent event) {
        try {
            empresa.actualizarCliente(
                    txtCedula.getText(),
                    txtNombre.getText(),
                    txtTelefono.getText(),
                    txtEmail.getText(),
                    txtCiudad.getText(),
                    txtDireccion.getText()
            );
            limpiarCamposCliente();
            tableViewClientes.getItems().clear();
            tableViewClientes.setItems(getListaClientes());
            mostrarMensaje("Notificacion AlquilaFacil", "Cliente Actualizado", "El cliente " + txtNombre.getText() +
                    " ha sido actualizado", Alert.AlertType.INFORMATION);
        } catch (ClienteNoRegistradoException e) {
            mostrarMensaje("Notificación AlquilaFacil", "Información invalida", e.getMessage(), Alert.AlertType.ERROR);
        } catch (AtributosVaciosException e) {
            mostrarMensaje("Notificación AlquilaFacil", "Información invalida", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Retorna un valo booleano si se pudo crear el cliente
     * @param cedula
     * @param nombre
     * @param telefono
     * @param email
     * @param ciudad
     * @param direccion
     */
    @FXML
    void crearCliente(ActionEvent event) {
        try {
            empresa.crearCliente(
                    txtCedula.getText(),
                    txtNombre.getText(),
                    txtTelefono.getText(),
                    txtEmail.getText(),
                    txtCiudad.getText(),
                    txtDireccion.getText()
            );
            limpiarCamposCliente();

            // Añade cliente a la lista local
            listadoClientes.add(clienteSeleccion);

            //Añade cliente a la table view
            tableViewClientes.getItems().clear();
            tableViewClientes.setItems(getListaClientes());
            mostrarMensaje("Notificacion AlquilaFacil", "Cliente Registrado", "El cliente " + txtNombre.getText() +
                    "ha sido registrado", Alert.AlertType.INFORMATION);
        } catch (AtributosVaciosException e){
            mostrarMensaje("Notificacion AlquilaFacil", "Los campos estan vacios", "Los campos en el registro del cliente" +
                    " estan vacios", Alert.AlertType.WARNING);
        } catch (CLienteYaExistenteException e) {
            mostrarMensaje("Notificacion AlquilaFacil", "Cliente no registrado", "El cliente con cedula" + txtCedula.getText() +
                    " ya existe", Alert.AlertType.WARNING);
        }
    }

    /**
     * Elimina un cliente dado su cedula
     * @param cedula
     */
    @FXML
    void eliminarCliente(ActionEvent event) {
        txtCedula.setDisable(false);
        try {
            if (clienteSeleccion != null) {
                String cedula = clienteSeleccion.getCedula();
                empresa.eliminarCliente(cedula);
                listadoClientes.remove(clienteSeleccion);  // Elimina de la lista local
                limpiarCamposCliente();
                tableViewClientes.getItems().clear();
                tableViewClientes.setItems(getListaClientes());
                mostrarMensaje("Notificación AlquilaFacil", "Información valida", "El cliente ha sido eliminado de manera correcta", Alert.AlertType.INFORMATION);
            } else {
                mostrarMensaje("Notificación AlquilaFacil", "Información invalida", "Por favor seleccione un cliente en la tabla", Alert.AlertType.INFORMATION);
            }
        } catch (ClienteNoRegistradoException e) {
            mostrarMensaje("Notificación AlquilaFacil", "Información invalida", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Limpia los campos de un cliente cuando este se selcciona
     * @param event
     */
    @FXML
    void nuevoCliente(ActionEvent event) {
        limpiarCamposCliente();
    }

    /**
     * Regresa a la ventana de inicio
     * @param event
     */
    @FXML
    void regresar(ActionEvent event) {
        this.stage.close();
        inicioController.show();
        // Actualiza la lista de clientes en la tabla antes de cerrar la ventana
        tableViewClientes.getItems().setAll(getListaClientes());
    }
}
