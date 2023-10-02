package co.edu.uniquindio.controller;

import co.edu.uniquindio.app.AlquilaFacilApp;
import co.edu.uniquindio.model.Empresa;
import co.edu.uniquindio.model.Propiedades;
import co.edu.uniquindio.model.Registro;
import co.edu.uniquindio.model.Vehiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
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
    }

    @FXML
    void generarTotalGanadoGanancias(ActionEvent event) {

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

    @FXML
    void verMarcaVehiculoGanancias(ActionEvent event) {

    }

    @FXML
    void verVehiculosAlquiladosRegistro(ActionEvent event) {

    }
}
