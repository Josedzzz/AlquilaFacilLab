package co.edu.uniquindio.app;

import co.edu.uniquindio.controller.InicioController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AlquilaFacilApp extends Application {
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        mostrarVentanaInicio();
    }

    private void mostrarVentanaInicio() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AlquilaFacilApp.class.getResource("/views/InicioView.fxml"));
            BorderPane borderPane = (BorderPane) loader.load();
            InicioController inicioController = loader.getController();
            inicioController.setAlquilaFacilApp(this);
            Scene scene = new Scene(borderPane);
            primaryStage.setScene(scene);
            primaryStage.show();
            InicioController controller = loader.getController();
            controller.setStage(primaryStage);
        } catch (Exception e) {
            System.out.println("Problema");
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
