// com/weaverstudios/main/Main.java
package com.weaverstudios.main;

import javafx.application.Application;
import javafx.stage.Stage;

/*
 * Main class for launching the JavaFX application
 * This class extends Application, making it the entry point for JavaFX
 */
public class Main extends Application{

    // ========================================================================
    //     The start method is the entry point of the JavaFX application
    //     It is called after the JavaFX application has been initialized
    // ========================================================================
    @Override
    public void start(Stage primaryStage) {
        // Initialize the LanguageManager to set up language configuration automatically.
        LanguageManager.initialize();
        
        // Get the singleton instance of MainView.
        final MainView insMainView = MainView.getInstance();
        MainView mainView = insMainView;

        // Display the main view using the primary stage.
        mainView.show(primaryStage);
    }

    // ==============================================================================
    //    The main method serves as the standard Java entry point
    //    It calls the JavaFX launch method, which initializes the JavaFX runtime
    //    and triggers the start() method
    // ==============================================================================
    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
