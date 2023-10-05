package co.edu.uniquindio.controller;

import co.edu.uniquindio.app.AlquilaFacilApp;
import co.edu.uniquindio.exceptions.AtributosVaciosException;
import co.edu.uniquindio.exceptions.FechaInvalidaException;
import co.edu.uniquindio.exceptions.ListaVaciaException;
import co.edu.uniquindio.model.*;
import co.edu.uniquindio.utilities.Propiedades;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DatosEmpresaController implements Initializable {
    @FXML
    private Button btnMarcaVehiculoGanancias;

    @FXML
    private Button btnRegresarGanancias;

    @FXML
    private Button btnRegresarRegistro;

    @FXML
    private Button btnVehiculosAlquiladosRegistro;

    @FXML
    private Button btnaTotalGanadoGanancias;

    @FXML
    private TableView<Registro> tableViewRegistrosGanancias;

    @FXML
    private TableColumn<Registro, String> columnCedulaGanancias;

    @FXML
    private TableColumn<Registro, String> columnFacturaGanancias;

    @FXML
    private TableColumn<Registro, String> columnFechaFinalGanancias;

    @FXML
    private TableColumn<Registro, String> columnFechaInicalGanancias;

    @FXML
    private TableColumn<Registro, String> columnFechaRegistroGanancias;

    @FXML
    private TableColumn<Registro, String> columnPlacaGanancias;

    @FXML
    private TableView<Vehiculo> tableViewVehiculosRegistro;

    @FXML
    private TableColumn<Vehiculo, String> columnCajaRegistro;

    @FXML
    private TableColumn<Vehiculo, String> columnKilometrajeRegistro;

    @FXML
    private TableColumn<Vehiculo, String> columnMarcaRegistro;

    @FXML
    private TableColumn<Vehiculo, String> columnModeloRegistro;

    @FXML
    private TableColumn<Vehiculo, String> columnPlacaRegistro;

    @FXML
    private TableColumn<Vehiculo, String> columnPrecioRegistro;

    @FXML
    private TableColumn<Vehiculo, String> columnReferenciaRegistro;

    @FXML
    private TableColumn<Vehiculo, String> columnSillasRegistro;

    @FXML
    private DatePicker dateFechaFinalGanancias;

    @FXML
    private DatePicker dateFechaFinalRegistro;

    @FXML
    private DatePicker dateFechaInicialGanancias;

    @FXML
    private DatePicker dateFechaInicialRegistro;

    @FXML
    private ImageView imageVehiculoRegistro;

    @FXML
    private Label lblFechaFinalGanancias;

    @FXML
    private Label lblFechaFinalRegistro;

    @FXML
    private Label lblFechaInicialGanancias;

    @FXML
    private Label lblFechaInicialRegistro;

    @FXML
    private Label lblGanancias;

    @FXML
    private Label lblMarcaGanancias;

    @FXML
    private Label lblSubtituloGanancias;

    @FXML
    private Label lblSubtituloRegistro;

    @FXML
    private Label lblTituloGanancias;

    @FXML
    private Label lblTituloRegistros;

    @FXML
    private TextField txtGanancias;

    @FXML
    private TextField txtMarcaGanancias;

    @FXML
    private Tab tabGanancias;

    @FXML
    private Tab tabRegistros;


    //Variables auxiliares
    private AlquilaFacilApp alquilaFacilApp;
    private Stage stage;
    private InicioController inicioController;
    private ObservableList<Vehiculo> listadoVehiculos = FXCollections.observableArrayList();
    private Vehiculo vehiculoSeleccion;
    private ObservableList<Registro> listadoRegistros = FXCollections.observableArrayList();
    private LocalDate fechaInicialFiltrarRegistro;
    private LocalDate fechaFinalFiltrarRegistro;
    private LocalDate fechaInicialFiltrarGanancias;
    private LocalDate fechaFinalFiltrarGanancias;


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
        tabRegistros.setText(propiedades.getResourceBundle().getString("tabRegistrosDatosEmpresaView"));
        lblTituloRegistros.setText(propiedades.getResourceBundle().getString("lblTituloRegistrosDatosEmpresaView"));
        lblSubtituloRegistro.setText(propiedades.getResourceBundle().getString("lblSubtituloRegistroDatosEmpresaView"));
        btnRegresarRegistro.setText(propiedades.getResourceBundle().getString("btnRegresarRegistroDatosEmpresaView"));
        lblFechaInicialRegistro.setText(propiedades.getResourceBundle().getString("lblFechaInicialRegistroDatosEmpresaView"));
        lblFechaFinalRegistro.setText(propiedades.getResourceBundle().getString("lblFechaFinalRegistroDatosEmpresaView"));
        btnVehiculosAlquiladosRegistro.setText(propiedades.getResourceBundle().getString("btnVehiculosAlquiladosRegistroDatosEmpresaView"));
        columnPlacaRegistro.setText(propiedades.getResourceBundle().getString("columnPlacaAlquilarVehiculoView"));
        columnReferenciaRegistro.setText(propiedades.getResourceBundle().getString("columnReferenciaAlquilarVehiculoView"));
        columnMarcaRegistro.setText(propiedades.getResourceBundle().getString("columnMarcaAlquilarVehiculoView"));
        columnModeloRegistro.setText(propiedades.getResourceBundle().getString("columnModeloAlquilarVehiculoView"));
        columnKilometrajeRegistro.setText(propiedades.getResourceBundle().getString("columnKilometrajeAlquilarVehiculoView"));
        columnPrecioRegistro.setText(propiedades.getResourceBundle().getString("columnPrecioAlquilarVehiculoView"));
        columnCajaRegistro.setText(propiedades.getResourceBundle().getString("columnCajaAlquilarVehiculoView"));
        columnSillasRegistro.setText(propiedades.getResourceBundle().getString("columnSillasAlquilarVehiculoView"));

        tabGanancias.setText(propiedades.getResourceBundle().getString("tabGananciasDatosEmpresaView"));
        lblTituloGanancias.setText(propiedades.getResourceBundle().getString("lblTituloGananciasDatosEmpresaView"));
        btnRegresarGanancias.setText(propiedades.getResourceBundle().getString("btnRegresarRegistroDatosEmpresaView"));
        lblFechaInicialGanancias.setText(propiedades.getResourceBundle().getString("lblFechaInicialRegistroDatosEmpresaView"));
        lblFechaFinalGanancias.setText(propiedades.getResourceBundle().getString("lblFechaFinalRegistroDatosEmpresaView"));
        lblGanancias.setText(propiedades.getResourceBundle().getString("lblGananciasDatosEmpresaView"));
        txtGanancias.setPromptText(propiedades.getResourceBundle().getString("txtGananciasDatosEmpresaView"));
        lblMarcaGanancias.setText(propiedades.getResourceBundle().getString("lblMarcaGananciasDatosEmpresaView"));
        txtMarcaGanancias.setPromptText(propiedades.getResourceBundle().getString("txtMarcaGananciasDatosEmpresaView"));
        btnMarcaVehiculoGanancias.setText(propiedades.getResourceBundle().getString("btnMarcaVehiculoGananciasDatosEmpresaView"));
        btnaTotalGanadoGanancias.setText(propiedades.getResourceBundle().getString("btnaTotalGanadoGananciasDatosEmpresaView"));
        columnPlacaGanancias.setText(propiedades.getResourceBundle().getString("columnPlacaAlquilarVehiculoView"));
        columnFacturaGanancias.setText(propiedades.getResourceBundle().getString("columnFacturaGananciasDatosEmpresaView"));
        columnCedulaGanancias.setText(propiedades.getResourceBundle().getString("columnCedulaGananciasDatosEmpresaView"));
        columnFechaRegistroGanancias.setText(propiedades.getResourceBundle().getString("columnFechaRegistroGananciasDatosEmpresaView"));
        columnFechaInicalGanancias.setText(propiedades.getResourceBundle().getString("lblFechaInicialRegistroDatosEmpresaView"));
        columnFechaFinalGanancias.setText(propiedades.getResourceBundle().getString("lblFechaFinalRegistroDatosEmpresaView"));

        //PESTANIA DE REGISTROS

        //Datos de la tableView de vehiculos
        this.columnPlacaRegistro.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getPlaca()));
        this.columnReferenciaRegistro.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getReferencia()));
        this.columnMarcaRegistro.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getMarcaVehiculo().toString()));
        this.columnModeloRegistro.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getModelo()));
        this.columnKilometrajeRegistro.setCellValueFactory(e -> {
            double kilometraje = e.getValue().getKilometraje();
            String kilometrajeString = String.valueOf(kilometraje);
            return new ReadOnlyStringWrapper(kilometrajeString);
        });
        this.columnPrecioRegistro.setCellValueFactory(e -> {
            double precio = e.getValue().getPrecioAlquiler();
            String precioString = String.valueOf(precio);
            return new ReadOnlyStringWrapper(precioString);
        });
        this.columnCajaRegistro.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getTipoCajaVehiculo().toString()));
        this.columnSillasRegistro.setCellValueFactory( e -> {
            int sillas = e.getValue().getNumSillas();
            String sillasString = String.valueOf(sillas);
            return new ReadOnlyStringWrapper(sillasString);
        });
        //Selecciono vehiculos de la tabla
        tableViewVehiculosRegistro.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                vehiculoSeleccion = newSelection;
                vehiculoSeleccion = tableViewVehiculosRegistro.getSelectionModel().getSelectedItem();
            }
        });
        //Menejo de la fecha inicial y de la fecha final para los filtros
        dateFechaInicialRegistro.setOnAction(event -> {
            fechaInicialFiltrarRegistro = dateFechaInicialRegistro.getValue();
        });
        dateFechaFinalRegistro.setOnAction(event -> {
            fechaFinalFiltrarRegistro = dateFechaFinalRegistro.getValue();
        });

        //PESTANIA DE GANANCIAS

        //Datos de la tableVIew de registros
        this.columnPlacaGanancias.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getVehiculo().getPlaca()));
        this.columnFacturaGanancias.setCellValueFactory(e -> {
            double gananacias = e.getValue().getPrecioFactura();
            String gananciasString = String.valueOf(gananacias);
            return new ReadOnlyStringWrapper(gananciasString);
        });
        this.columnCedulaGanancias.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCliente().getCedula()));
        this.columnFechaRegistroGanancias.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFechaRegistro().toString()));
        this.columnFechaInicalGanancias.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFechaInicio().toString()));
        this.columnFechaFinalGanancias.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFechaRegreso().toString()));

        //Manejo de la fecha inicial y de la fecha final para las ganancias
        dateFechaInicialGanancias.setOnAction(event -> {
            fechaInicialFiltrarGanancias = dateFechaInicialGanancias.getValue();
        });
        dateFechaFinalGanancias.setOnAction(event -> {
            fechaFinalFiltrarGanancias = dateFechaFinalGanancias.getValue();
        });
    }

    /**
     * Genera el total de ganancias en un rango de fechas
     * @param event
     */
    @FXML
    void generarTotalGanadoGanancias(ActionEvent event) {
        try {
            empresa.validarFechas(fechaInicialFiltrarGanancias, fechaFinalFiltrarGanancias);
            double ganancias = empresa.calcularTotalGanado(fechaInicialFiltrarGanancias, fechaFinalFiltrarGanancias);
            tableViewRegistrosGanancias.getItems().clear();
            tableViewRegistrosGanancias.setItems(getRegistrosEnFechas(fechaInicialFiltrarGanancias, fechaFinalFiltrarGanancias));
            txtGanancias.setText(String.valueOf(ganancias));
            mostrarMensaje(propiedades.getResourceBundle().getString("mostrarMensajeTitulo"), propiedades.getResourceBundle().getString("mostrarMensajeHeaderAfirmativo"), propiedades.getResourceBundle().getString("generarTotalGanandoGanancias") + " " + ganancias, Alert.AlertType.INFORMATION);
        } catch (AtributosVaciosException e) {
            mostrarMensaje(propiedades.getResourceBundle().getString("mostrarMensajeTitulo"), propiedades.getResourceBundle().getString("mostrarMensajeHeaderNegativo"), e.getMessage(), Alert.AlertType.ERROR);
        } catch (FechaInvalidaException e) {
            mostrarMensaje(propiedades.getResourceBundle().getString("mostrarMensajeTitulo"), propiedades.getResourceBundle().getString("mostrarMensajeHeaderNegativo"), e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private ObservableList<Registro> getRegistrosEnFechas(LocalDate fechaInicial, LocalDate fechaFinal) {
        listadoRegistros.addAll(empresa.obtenerRegistrosEnFechas(fechaInicial, fechaFinal));
        return listadoRegistros;
    }

    /**
     * Regresa a la ventana inicial
     * @param event
     */
    @FXML
    void regresarGanancias(ActionEvent event) {
        this.stage.close();
        inicioController.show();
    }

    /**
     * Regresa a la ventana inicial
     * @param event
     */
    @FXML
    void regresarRegistro(ActionEvent event) {
        this.stage.close();
        inicioController.show();
    }

    /**
     * Muestra la marca de vehiculo mas alquilada
     * @param event
     */
    @FXML
    void verMarcaVehiculoGanancias(ActionEvent event) {
        try {
            MarcaVehiculo marcaVehiculo = empresa.encontrarMarcaMasAlquilada();
            txtMarcaGanancias.setText(marcaVehiculo.toString());
            mostrarMensaje(propiedades.getResourceBundle().getString("mostrarMensajeTitulo"), propiedades.getResourceBundle().getString("mostrarMensajeHeaderAfirmativo"), propiedades.getResourceBundle().getString("verMarcaVehiculoGanancias") + " " + marcaVehiculo.toString(), Alert.AlertType.INFORMATION);
        } catch (ListaVaciaException e) {
            mostrarMensaje(propiedades.getResourceBundle().getString("mostrarMensajeTitulo"), propiedades.getResourceBundle().getString("mostrarMensajeHeaderNegativo"), e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void verVehiculosAlquiladosRegistro(ActionEvent event) {
        try {
            empresa.validarFechas(fechaInicialFiltrarRegistro, fechaFinalFiltrarRegistro);
            tableViewVehiculosRegistro.getItems().clear();
            tableViewVehiculosRegistro.setItems(getListaVehiculosAlquilados(fechaInicialFiltrarRegistro, fechaFinalFiltrarRegistro));
            mostrarMensaje(propiedades.getResourceBundle().getString("mostrarMensajeTitulo"), propiedades.getResourceBundle().getString("mostrarMensajeHeaderAfirmativo"), propiedades.getResourceBundle().getString("verVehiculosAlquiladosRegistro"), Alert.AlertType.INFORMATION);
        } catch (AtributosVaciosException e) {
            mostrarMensaje(propiedades.getResourceBundle().getString("mostrarMensajeTitulo"), propiedades.getResourceBundle().getString("mostrarMensajeHeaderNegativo"), e.getMessage(), Alert.AlertType.ERROR);
        } catch (FechaInvalidaException e) {
            mostrarMensaje(propiedades.getResourceBundle().getString("mostrarMensajeTitulo"), propiedades.getResourceBundle().getString("mostrarMensajeHeaderNegativo"), e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Obtiene la lista de vehiculos alquilados
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    private ObservableList<Vehiculo> getListaVehiculosAlquilados(LocalDate fechaInicial, LocalDate fechaFinal) {
        listadoVehiculos.addAll(empresa.obtenerVehiculosAlquilados(fechaInicial, fechaFinal));
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
