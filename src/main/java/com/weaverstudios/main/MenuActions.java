// com/weaverstudios/main/MenuActions.java
package com.weaverstudios.main;

import java.io.File;
import java.util.Locale;
import java.util.Optional;

import com.weaverstudios.utils.GlobalUtils;

import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class MenuActions {
    // ====== SINGLETON ======
    private static MenuActions insMenAct = new MenuActions();
    private MenuActions (){} // This allows to create an instance of this class for calling methods reasons
    public static MenuActions getInstance() {
        return insMenAct;
    }


    private UIManager insUIMan = UIManager.getInstance();
    //private MainView insMainView = MainView.getInstance();


    // =======================
    //       File menu
    // =======================
    //private MainView insMainView = MainView.getInstance();
    public static void newProjectAction(Stage currentStage) {
        // Prompt user to enter project name
        TextInputDialog nameDialog = new TextInputDialog("NewProject");
        nameDialog.setTitle(LanguageManager.getText("project.creation.setName"));
        nameDialog.setHeaderText(LanguageManager.getText("project.creation.headText"));
        nameDialog.setContentText(LanguageManager.getText("project.creation.contText"));

        Optional<String> result = nameDialog.showAndWait();

        if (result.isPresent() && !result.get().trim().isEmpty()) {
            String projectName = result.get().trim();

            // Show DirectoryChooser for the project location
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle(LanguageManager.getText("project.creation.directoryChooser"));

            File selectedDirectory = directoryChooser.showDialog(currentStage);

            if (selectedDirectory != null) {
                // Create the new project folder with the user-defined name
                File newProjectFolder = new File(selectedDirectory, projectName);

                if (!newProjectFolder.exists()) {
                    if (newProjectFolder.mkdir()) {
                        System.out.println("Project folder created at: " + newProjectFolder.getAbsolutePath());

                        // Close the current window
                        //currentStage.close();

                        // Load the new project into the application
                        //loadProject(newProjectFolder);
                    } else {
                        // FAILED TO CREATE THE PROJECT FOLDER
                        System.out.println("Failed to create the project folder.");
                        GlobalUtils.showError(LanguageManager.getText("error.failCreateFolder.title"),
                                              LanguageManager.getText("error.failCreateFolder"));
                    }
                } else {
                    // FOLDER ALREADY EXISTS
                    System.out.println("A folder with this name already exists.");
                    // Show error if the folder already exists
                    GlobalUtils.showError(LanguageManager.getText("error.folderAlreadyExist.title"),
                                          LanguageManager.getText("error.folderAlreadyExist"));
                }
            } else {
                System.out.println("No directory selected.");
            }
        } else {
            System.out.println("No project name entered.");
        }
    }

    public static void openAction(Stage currentStage) {
        // Create a DirectoryChooser instance to allow the user to select a project folder
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(LanguageManager.getText("project.open.directoryChooser"));

        // Open the folder selection dialog and store the selected folder
        File selectedFolder = directoryChooser.showDialog(currentStage);

        // Check if the user selected a valid folder
        if (selectedFolder != null && selectedFolder.isDirectory()) {
            // Store the selected folder as the current project folder
            ProjectManager.setCurrentProjectFolder(selectedFolder);

            // Print the selected folder path for debugging purposes
            System.out.println("Selected project folder: " + ProjectManager.getCurrentProjectFolder().getAbsolutePath());

            // Load and process the project files within the selected folder
            ProjectManager.loadProjectFiles(ProjectManager.getCurrentProjectFolder());
        } else {
            // Notify the user that no valid folder was selected
            System.out.println("No valid folder selected");
        }
    }

    public static void importFileAction() {

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

    public VBox settingsAction() {
        VBox settingsPanel = new VBox(10);
        // Max width and height of settingsPanel in %, so it doesn't show problems with the screen size and it adjust to the
        // scene size also
        settingsPanel.maxWidthProperty().bind(MainView.getMainContent().widthProperty().multiply(0.2));
        settingsPanel.maxHeightProperty().bind(MainView.getMainContent().heightProperty().multiply(0.1));

        // ===== LANGUAGES ===== //
        MenuButton languages = GlobalUtils.menuButton("lang.menu");
        MenuItem systemLang = GlobalUtils.menuItem("lang.systemLang");
        MenuItem spanish = GlobalUtils.menuItem("lang.es");
        MenuItem english = GlobalUtils.menuItem("lang.en");
        systemLang.setOnAction(e -> {
            LanguageManager.setLanguage(Locale.of(LanguageManager.getSystemLang()));
            UIManager.updateLanguageLaunch();
            MainView.setLocale(Locale.of(LanguageManager.getSystemLang()));
            // Update the language selected in Preferences
            PreferencesManager.setLanguage(LanguageManager.getSystemLang());
        });
        spanish.setOnAction(e -> {
            LanguageManager.setLanguage(Locale.of("es"));
            UIManager.updateLanguageLaunch();
            MainView.setLocale(Locale.of("es"));
            // Update the language selected in Preferences
            PreferencesManager.setLanguage("es");
        });
        english.setOnAction(e -> {
            LanguageManager.setLanguage(Locale.of("en"));
            UIManager.updateLanguageLaunch();
            MainView.setLocale(Locale.of("en"));
            // Update the language selected in Preferences
            PreferencesManager.setLanguage("en");
        });
        languages.getItems().addAll(systemLang, spanish, english);

        // ===== THEME SWITCHING ===== //
        MenuButton themeSwitch = GlobalUtils.menuButton("theme.switch");
        MenuItem darkTheme = GlobalUtils.menuItem("theme.dark");
        MenuItem lightTheme = GlobalUtils.menuItem("theme.light");
        darkTheme.setOnAction(e -> {
            // First, set the new currentTheme
            currentTheme = "/com/weaverstudios/css/darkMode.css";
            // Update the theme selected in Preferences
            PreferencesManager.setTheme(currentTheme);
            // Then, call the method updateStyles() using the scene and the current theme
            insUIMan.updateStyles(MainView.getScene(), currentTheme);
        });
        lightTheme.setOnAction(e -> {
            // First, set the new currentTheme
            currentTheme = "/com/weaverstudios/css/lightMode.css";
            // Update the theme selected in Preferences
            PreferencesManager.setTheme(currentTheme);
            // Then, call the method updateStyles() using the scene and the current theme
            insUIMan.updateStyles(MainView.getScene(), currentTheme);
        });
        themeSwitch.getItems().addAll(darkTheme, lightTheme);

        // ===== CLOSE SETTINGS ===== //
        Button closeSettings = GlobalUtils.closeButton(settingsPanel);
        // Set the content of the mainContent to null
        //closeSettings.setOnAction(e -> MainView.getMainContainer().getChildren().remove(settingsPanel));

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