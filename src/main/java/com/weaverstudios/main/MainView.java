package com.weaverstudios.main;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainView {

    private StackPane root; // Main container (overlay)

    public void show(Stage primaryStage) {
        // Crear la interfaz
        BorderPane mainContent = new BorderPane();

        // Obtener el tamaño de la pantalla
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // Crear la barra superior
        mainContent.setTop(createMenuBar());

        // Crear el StackPanel y agregar el contenido
        root = new StackPane(mainContent);

        // Crear la escena y mostrarla
        Scene scene = new Scene(root, screenWidth, screenHeight);

        primaryStage.setTitle("Textor Mundi");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true); // Iniciar en ventana maximizada
        primaryStage.show();
    }

    // MenuBar (File, View, Settings, etc)
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        
        // File menu
        Menu menuFile = new Menu();

        MenuItem create = new MenuItem();
        MenuItem open = new MenuItem();
        MenuItem save = new MenuItem();
        MenuItem exit = new MenuItem();

        return menuBar;
    }
}