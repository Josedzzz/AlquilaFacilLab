package co.edu.uniquindio.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AlquilaFacilApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(AlquilaFacilApp.class.getResource("/views/inicioView.fxml") );
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Mi agencia");
        stage.show();

    }

    public static void main(String[] args) {
        launch(AlquilaFacilApp.class, args);
    }
}
