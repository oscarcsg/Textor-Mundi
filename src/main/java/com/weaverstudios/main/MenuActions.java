package com.weaverstudios.main;

import java.util.Locale;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class MenuActions {
    // =======================
    //       File menu
    // =======================
    public static void createAction() {

    }
    public static void openAction() {

    }
    public static void saveAction() {

    }
    public static void saveAsAction() {
        
    }
    public static void closeAction() {
        
    }


    // =======================
    //       Edit menu
    // =======================
    public static void copyAction() {

    }
    public static void cutAction() {
        
    }
    public static void pasteAction() {
        
    }
    public static void selectAllAction() {

    }


    // =======================
    //       View menu
    // =======================
    public static void fullscreenAction() {
        
    }
    public static void zoomInAction() {
        
    }
    public static void zoomOutAction() {

    }


    // =======================
    //       Tools menu
    // =======================
    public static VBox settingsAction() {
        VBox settingsPanel = new VBox(10);
        settingsPanel.setMaxWidth(200);
        settingsPanel.setMaxHeight(100);

        MenuButton languages = new MenuButton(LanguageManager.getText("lang.menu"));
        MenuItem spanish = new MenuItem(LanguageManager.getText("lang.es"));
        MenuItem english = new MenuItem(LanguageManager.getText("lang.en"));

        spanish.setOnAction(e -> LanguageManager.setLanguage(Locale.of("es")));
        english.setOnAction(e -> {
            LanguageManager.setLanguage(Locale.of("en"));
            MainView.update();
        });

        languages.getItems().addAll(spanish, english);

        settingsPanel.getChildren().addAll(
            languages
        );
        return settingsPanel;
    }


    // =======================
    //       Help menu
    // =======================
    public static void documentationAction() {

    }
    public static void KBshortcutsAction() {
        
    }
    public static void aboutAction() {
        
    }
    public static void reportBugAction() {

    }
}