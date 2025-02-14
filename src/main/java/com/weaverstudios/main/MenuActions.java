//com/weaverstudios/main/MenuActions.java
package com.weaverstudios.main;

import java.util.Locale;

import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class MenuActions {
    // ====== SINGLETON ======
    private static MenuActions insMenAct = new MenuActions();
    private MenuActions (){} // This allows to create an instance of this class for calling methods reasons
    public static MenuActions getInstance() {
        return insMenAct;
    }

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
    //       Tools menu    // MenuActions.java
    // =======================
    private static String currentTheme = "/com/weaverstudios/css/lightMode.css";
    private UIManager insUIMan = UIManager.getInstance();

    public VBox settingsAction() {
        VBox settingsPanel = new VBox(10);
        settingsPanel.setMaxWidth(200);
        settingsPanel.setMaxHeight(100);

        // ===== LANGUAGES ===== //
        MenuButton languages = new MenuButton(LanguageManager.getText("lang.menu"));
        MenuItem systemLang = new MenuItem(LanguageManager.getText("lang.systemLang"));
        MenuItem spanish = new MenuItem(LanguageManager.getText("lang.es"));
        MenuItem english = new MenuItem(LanguageManager.getText("lang.en"));
        systemLang.setOnAction(e -> {
            LanguageManager.setLanguage(Locale.of(LanguageManager.getSystemLang()));
            UIManager.updateLanguageLaunch();
            MainView.setLocale(Locale.of(LanguageManager.getSystemLang()));
        });
        spanish.setOnAction(e -> {
            LanguageManager.setLanguage(Locale.of("es"));
            UIManager.updateLanguageLaunch();
            MainView.setLocale(Locale.of("es"));
        });
        english.setOnAction(e -> {
            LanguageManager.setLanguage(Locale.of("en"));
            UIManager.updateLanguageLaunch();
            MainView.setLocale(Locale.of("en"));
        });
        languages.getItems().addAll(systemLang, spanish, english);

        // ===== THEME SWITCHING ===== //
        MenuButton themeSwitch = new MenuButton(LanguageManager.getText("theme.switch"));
        MenuItem systemTheme = new MenuItem(LanguageManager.getText("theme.system"));
        MenuItem darkTheme = new MenuItem(LanguageManager.getText("theme.dark"));
        MenuItem lightTheme = new MenuItem(LanguageManager.getText("theme.light"));
        darkTheme.setOnAction(e -> {
            // First, set the new currentTheme
            currentTheme = "/com/weaverstudios/css/darkMode.css";
            // Then, call the method updateStyles() using the scene and the current theme
            insUIMan.updateStyles(MainView.getScene(), currentTheme);
        });
        lightTheme.setOnAction(e -> {
            // First, set the new currentTheme
            currentTheme = "/com/weaverstudios/css/lightMode.css";
            // Then, call the method updateStyles() using the scene and the current theme
            insUIMan.updateStyles(MainView.getScene(), currentTheme);
        });
        themeSwitch.getItems().addAll(systemTheme, darkTheme, lightTheme);

        // ===== CLOSE SETTINGS ===== //
        Button closeSettings = new Button(LanguageManager.getText("button.close"));
        // Set the content of the mainContent to null
        closeSettings.setOnAction(e -> MainView.getMainContent().setCenter(null));

        settingsPanel.getChildren().addAll(
            languages,
            themeSwitch,

            closeSettings
        );
        return settingsPanel;
    }

    public static String getCurrentTheme() {
        return currentTheme;
    }

    public static void setCurrentTheme(String newTheme) {
        currentTheme = newTheme;
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