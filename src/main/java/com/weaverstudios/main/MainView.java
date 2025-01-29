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
        // Interface creation
        BorderPane mainContent = new BorderPane();

        // Obtain screen size and resolution
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // Create topBar, MenuBar
        mainContent.setTop(createMenuBar());

        // Create StackPanel and add its content
        root = new StackPane(mainContent);

        // Create scene and show
        Scene scene = new Scene(root, screenWidth, screenHeight);

        primaryStage.setTitle("Textor Mundi");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true); // Initialize maximazed windows
        primaryStage.show();
    }

    // MenuBar (File, View, Settings, etc)
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        
        // File menu
        Menu menuFile = new Menu(LanguageManager.getText("menu.file"));
        MenuItem create = new MenuItem();
        MenuItem open = new MenuItem();
        MenuItem save = new MenuItem();
        MenuItem saveAs = new MenuItem();
        MenuItem close = new MenuItem();
        MenuItem exit = new MenuItem();
        menuFile.getItems().addAll(create, open, save, saveAs, close, exit);


        // Edit menu
        Menu menuEdit = new Menu();
        MenuItem copy = new MenuItem();
        MenuItem cut = new MenuItem();
        MenuItem paste = new MenuItem();
        MenuItem selectAll = new MenuItem();
        menuEdit.getItems().addAll(copy, cut, paste, selectAll);


        // View menu
        Menu menuView = new Menu();
        MenuItem fullscreen = new MenuItem();
        MenuItem zoomIn = new MenuItem();
        MenuItem zoomOut = new MenuItem();
        menuView.getItems().addAll(fullscreen, zoomIn, zoomOut);


        // Tools menu
        Menu menuTools = new Menu();
        MenuItem settings = new MenuItem();
        menuTools.getItems().addAll(settings);


        // Help menu
        Menu menuHelp = new Menu();
        MenuItem documentation = new MenuItem();
        MenuItem KBshortcuts = new MenuItem();
        MenuItem about = new MenuItem();
        MenuItem reportBug = new MenuItem();
        menuHelp.getItems().addAll(documentation, KBshortcuts, about, reportBug);


        menuBar.getMenus().addAll(menuFile);
        return menuBar;
    }
}