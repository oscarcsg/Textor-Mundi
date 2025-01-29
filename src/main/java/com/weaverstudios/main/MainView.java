package com.weaverstudios.main;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainView {
    private Button togglePanelButton;
    private Button newEventButton;
    private Text projectNameText;
    private VBox sidePanel;
    private static VBox eventCreationPanel; // Panel de creación de los eventos
    private StackPane root; // Main container (overlay)
    private static boolean eventPanelVisible = false; // Controlar la visibilidad del panel de eventos

    public void show(Stage primaryStage) {
        // Crear la interfaz
        BorderPane mainContent = new BorderPane();

        // Obtener el tamaño de la pantalla
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // Crear la barra superior
        BorderPane topBar = createTopBar();
        mainContent.setTop(topBar);

        // Crear el panel lateral retráctil
        sidePanel = createSidePanel();
        sidePanel.setVisible(false); // Panel ocultado inicialmente
        mainContent.setLeft(sidePanel);

        // Crear el StackPanel y agregar ambos nodos
        root = new StackPane(mainContent);

        // Crear la escena y mostrarla
        Scene scene = new Scene(root, screenWidth, screenHeight);

        primaryStage.setTitle("Línea Temporal");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true); // Iniciar en ventana maximizada
        primaryStage.show();
    }

//------------------------------------------------------------------------
//------------------------BARRA SUPERIOR----------------------------------
//------------------------------------------------------------------------

    private BorderPane createTopBar() {
        BorderPane topBar = new BorderPane();

        // Botón para desplegar el panel lateral
        togglePanelButton = new Button("☰");
        topBar.setLeft(togglePanelButton);
        togglePanelButton.setOnAction(e -> sidePanel.setVisible(!sidePanel.isVisible()));

        // Texto del nombre del proyecto
        projectNameText = new Text("Proyecto de Línea Temporal");
        topBar.setCenter(projectNameText);

        // Botón para agregar un nuevo evento
        newEventButton = new Button("Nuevo Evento");
        newEventButton.setOnAction(e -> toggleEventCreationPanel()); // Al hacer click, mostrar panel de eventos

        topBar.setRight(newEventButton);

        return topBar;
    }


//------------------------------------------------------------------------
//------------------------PANEL LATERAL-----------------------------------
//------------------------------------------------------------------------

    private VBox createSidePanel(){
        VBox sidePanel = new VBox(10);

        // Opciones del panel lateral
        Button createLineButton = new Button("Crear nueva línea temporal");
        Button openLineButton = new Button("Abrir línea temporal");
        Button saveLineButton = new Button("Guardar línea temporal");
        Button saveAsButton = new Button("Guardar línea temporal como...");

        sidePanel.getChildren().addAll(createLineButton, openLineButton, saveLineButton, saveAsButton);
        return sidePanel;
    }

//------------------------------------------------------------------------
//------------MOSTRAR Y OCULTAR PANEL DE CREACION DE EVENTOS--------------
//------------------------------------------------------------------------

    public static void toggleEventCreationPanel(){
        eventPanelVisible = !eventPanelVisible; // Cambiar visibilidad
        eventCreationPanel.setVisible(eventPanelVisible); // Mostrar u ocultar panel

        if (eventPanelVisible) {
            // Centrar el panel en el StackPanel
            StackPane.setAlignment(eventCreationPanel, Pos.BOTTOM_CENTER);
        }
    }
}