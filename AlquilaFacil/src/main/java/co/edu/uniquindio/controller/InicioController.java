package co.edu.uniquindio.controller;

import co.edu.uniquindio.app.AlquilaFacilApp;
import co.edu.uniquindio.model.Empresa;
import co.edu.uniquindio.utilities.Propiedades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicioController implements Initializable {

    @FXML
    private Button btnAlquilarVehiculo;

    @FXML
    private Button btnRegistrarCliente;

    @FXML
    private Button btnRegistrarVehiculo;

    @FXML
    private Label lblSubtitulo;

    @FXML
    private Label lblTitulo;

    @FXML
    private Hyperlink linkDatosEmpresa;

    //Variables auxiliares
    private AlquilaFacilApp alquilaFacilApp;
    private Stage stage;

    //Uso del singleton
    private final Empresa empresa = Empresa.getInstance();
    private final Propiedades propiedades = Propiedades.getInstance();

    public void setAlquilaFacilApp(AlquilaFacilApp alquilaFacilApp) {
        this.alquilaFacilApp = alquilaFacilApp;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Configuracion del idioma en el que se ven los textos de la app dependiendo el sistema operativo
        lblTitulo.setText(propiedades.getResourceBundle().getString("TituloInicioView"));
        lblSubtitulo.setText(propiedades.getResourceBundle().getString("SubtituloInicioView"));
        btnRegistrarCliente.setText(propiedades.getResourceBundle().getString("btnRegistrarClienteInicioView"));
        btnRegistrarVehiculo.setText(propiedades.getResourceBundle().getString("btnRegistrarVehiculoInicioView"));
        btnAlquilarVehiculo.setText(propiedades.getResourceBundle().getString("btnAlquilarVehiculoInicioView"));
        linkDatosEmpresa.setText(propiedades.getResourceBundle().getString("linkDatosEmpresaInicioView"));
    }

    /**
     * Lleva a la ventana alquilar vehiculo
     * @param event
     */
    @FXML
    void irAlquilarVehiculo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AlquilaFacilApp.class.getResource("/views/AlquilarVehiculoView.fxml"));
        BorderPane borderPane = (BorderPane) loader.load();
        AlquilarVehiculoController controller = loader.getController();
        controller.setAlquilaFacilApp(alquilaFacilApp);
        Scene scene = new Scene(borderPane);
        Stage stage = new Stage();
        stage.setTitle("Alquilar vehículo");
        stage.setScene(scene);
        controller.init(stage, this);
        stage.show();
        this.stage.close();
    }

    /**
     * Lleva a la ventana datos de la empresa
     * @param event
     */
    @FXML
    void irDatosEmpresa(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AlquilaFacilApp.class.getResource("/views/DatosEmpresaView.fxml"));
        BorderPane borderPane = (BorderPane) loader.load();
        DatosEmpresaController controller = loader.getController();
        controller.setAlquilaFacilApp(alquilaFacilApp);
        Scene scene = new Scene(borderPane);
        Stage stage = new Stage();
        stage.setTitle("Datos de la empresa");
        stage.setScene(scene);
        controller.init(stage, this);
        stage.show();
        this.stage.close();
    }

    /**
     * Lleva a la ventana de registro de clientes
     * @param event
     */
    @FXML
    void irRegistrarCliente(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AlquilaFacilApp.class.getResource("/views/RegistrarClienteView.fxml"));
        BorderPane borderPane = (BorderPane) loader.load();
        RegistrarClienteController controller = loader.getController();
        controller.setAlquilaFacilApp(alquilaFacilApp);
        Scene scene = new Scene(borderPane);
        Stage stage = new Stage();
        stage.setTitle("Registro de clientes");
        stage.setScene(scene);
        controller.init(stage, this);
        stage.show();
        this.stage.close();
    }

    /**
     * Lleva a la ventana de registro de vehiculos
     * @param event
     */
    @FXML
    void irRegistrarVehiculo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AlquilaFacilApp.class.getResource("/views/RegistrarVehiculoView.fxml"));
        BorderPane borderPane = (BorderPane) loader.load();
        RegistrarVehiculoController controller = loader.getController();
        controller.setAlquilaFacilApp(alquilaFacilApp);
        Scene scene = new Scene(borderPane);
        Stage stage = new Stage();
        stage.setTitle("Registro de vehículos");
        stage.setScene(scene);
        controller.init(stage, this);
        stage.show();
        this.stage.close();
    }

}

