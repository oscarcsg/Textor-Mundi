// com/weaverstudios/main/Main.java
package com.weaverstudios.main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) {
        // Initialize LanguageManager for automatic language configuration
        LanguageManager.initialize();
        
        final MainView insMainView = MainView.getInstance();
        MainView mainView = insMainView;
        mainView.show(primaryStage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}