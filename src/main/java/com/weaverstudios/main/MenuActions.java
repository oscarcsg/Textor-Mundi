// com/weaverstudios/main/MenuActions.java
package com.weaverstudios.main;

import com.weaverstudios.utils.GlobalUtils;

import java.io.File;
import java.util.Locale;
import java.util.Optional;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/*
 * This class manages actions associated with the application's menu
 * It includes functionality for file operations, editing, viewing, tools, and help options
 */
public class MenuActions {
    // ====== SINGLETON ======
    private static MenuActions insMenAct = new MenuActions();
    private MenuActions (){} // This allows to create an instance of this class for calling methods reasons
    public static MenuActions getInstance() {
        return insMenAct;
    }


    // Instance of UIManager to manage UI updates
    private UIManager insUIMan = UIManager.getInstance();
    //private MainView insMainView = MainView.getInstance();


    // =======================
    //       File menu
    // =======================

    /*
     * Handles the creation of a new project by prompting the user to enter a project name
     * and selecting a directory to store it
     */
    public static void newProjectAction(Stage currentStage) {
        // Prompt user to enter project name
        TextInputDialog nameDialog = new TextInputDialog("NewProject");
        nameDialog.setTitle(LanguageManager.getText("project.creation.setName"));
        nameDialog.setHeaderText(LanguageManager.getText("project.creation.headText"));
        nameDialog.setContentText(LanguageManager.getText("project.creation.contText"));

        Optional<String> result = nameDialog.showAndWait();

        // Check if the user entered a valid name
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
                        ButtonType userChoice = GlobalUtils.confirmationDialog(
                            "confirmationDialog.openProject.title",
                            "confirmationDialog.openProject.message",
                            "function.open",
                            "function.cancel"
                        );
                        if (userChoice.getText().equals(LanguageManager.getText("function.open"))) {
                            ProjectManager.setCurrentProjectFolder(newProjectFolder);
                            setDefaultProject(newProjectFolder);
                        }
                    } else {
                        // FAILED TO CREATE THE PROJECT FOLDER
                        System.out.println("Failed to create the project folder.");
                        // Show error if the project can't be created
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

    private static void setDefaultProject(File projectFolder) {
        ButtonType userChoice = GlobalUtils.confirmationDialog(
            "confirmationDialog.projectDefault.title",
            "confirmationDialog.setProjectDefault.message",
            "function.accept",
            "function.cancel"
        );
        if (userChoice.getText().equals(LanguageManager.getText("function.accept"))) {
            PreferencesManager.setDefaultProject(projectFolder);
        }
    }
    private static void quitDefaultProject() {
        ButtonType userChoice = GlobalUtils.confirmationDialog(
            "confirmationDialog.projectDefault.title",
            "confirmationDialog.quitProjectDefault.message",
            "function.accept",
            "function.cancel"
        );
        if (userChoice.getText().equals(LanguageManager.getText("function.accept"))) {
            PreferencesManager.setDefaultProject(null);
            System.out.println("Default project quit from preferences.");
        }
    }

    /*
     * Opens an existing project by allowing the user to select a project directory
     */
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
            setDefaultProject(selectedFolder);

            // Print the selected folder path for debugging purposes
            System.out.println("Selected project folder: " + ProjectManager.getCurrentProjectFolder().getAbsolutePath());

            // Load and process the project files within the selected folder
            ProjectManager.loadProjectFiles(ProjectManager.getCurrentProjectFolder());
        } else {
            // Notify the user that no valid folder was selected
            System.out.println("No valid folder selected");
            GlobalUtils.showError(
                "error.folderOppening.title",
                "error.folderOppening"
            );
        }
    }

    public static void importFileAction() {

    }

    public static void saveAction() {

    }

    public static void saveAsAction() {
        
    }

    public static void closeAction() {
        ProjectManager.setCurrentProjectFolder(null);
        quitDefaultProject();
        MainView.setDefaultMainView();
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
    //private static String currentTheme = "/com/weaverstudios/css/lightMode.css";

    /*
     * Creates and returns a settings panel with options // MenuActions.java
     */
    public VBox settingsAction() {
        VBox settingsPanel = new VBox(10);
        settingsPanel.setId("settingsPanel");

        /*
         * Max width and height of settingsPanel in %, so it doesn't show problems with the screen
         * size and it adjust to the scene size also
         */
        settingsPanel.maxWidthProperty().bind(MainView.getMainContent().widthProperty().multiply(0.2));
        settingsPanel.maxHeightProperty().bind(MainView.getMainContent().heightProperty().multiply(0.1));

        // ===== LANGUAGES ===== //
        MenuButton languages = GlobalUtils.menuButton("lang.menu");
        MenuItem systemLang = GlobalUtils.menuItem("lang.systemLang");
        MenuItem spanish = GlobalUtils.menuItem("lang.es");
        MenuItem english = GlobalUtils.menuItem("lang.en");
        systemLang.setOnAction(e -> {
            try {
                if (!PreferencesManager.getLanguage().equals(LanguageManager.getSystemLang())) {
                    LanguageManager.setLanguage(Locale.of(LanguageManager.getSystemLang()));
                    UIManager.updateLanguageLaunch();
                    MainView.setLocale(Locale.of(LanguageManager.getSystemLang()));
                    // Update the language selected in Preferences
                    PreferencesManager.setLanguage(LanguageManager.getSystemLang());
                } else System.out.println("User has selected the current language.");
            } catch (Exception ex) {
                System.out.println("An error occurred while changing the language.");
            }
        });
        spanish.setOnAction(e -> {
            try {
                if (!PreferencesManager.getLanguage().equals("es")) {
                    LanguageManager.setLanguage(Locale.of("es"));
                    UIManager.updateLanguageLaunch();
                    MainView.setLocale(Locale.of("es"));
                    // Update the language selected in Preferences
                    PreferencesManager.setLanguage("es");
                } else System.out.println("User has selected the current language.");
            } catch (Exception ex) {
                System.out.println("An error occurred while changing the language.");
            }
        });
        english.setOnAction(e -> {
            try {
                if (!PreferencesManager.getLanguage().equals("en")) {
                    LanguageManager.setLanguage(Locale.of("en"));
                    UIManager.updateLanguageLaunch();
                    MainView.setLocale(Locale.of("en"));
                    // Update the language selected in Preferences
                    PreferencesManager.setLanguage("en");
                } else System.out.println("User has selected the current language.");
            } catch (Exception ex) {
                System.out.println("An error occurred while changing the language.");
            }
        });
        languages.getItems().addAll(systemLang, spanish, english);

        // ===== PROJECTS LANGUAGE ===== //
        MenuButton projectsLanguage = GlobalUtils.menuButton("lang.projects.menu");
        MenuItem sysProjectsLang = GlobalUtils.menuItem("lang.systemLang");
        MenuItem spanishProjectsLang = GlobalUtils.menuItem("lang.es");
        MenuItem englishProjectsLang = GlobalUtils.menuItem("lang.en");
        sysProjectsLang.setOnAction(e -> {
            try {
                if (!PreferencesManager.getProjectsLang().equals(LanguageManager.getSystemLang())) {
                    PreferencesManager.setProjectsLang(LanguageManager.getSystemLang());
                } else System.out.println("User has selected the current projects language.");
            } catch (Exception ex) {
                System.out.println("An error occurred while changing the projects language.");
            }
        });
        spanishProjectsLang.setOnAction(e -> {
            try {
                if (!PreferencesManager.getProjectsLang().equals("es")) {
                    PreferencesManager.setProjectsLang("es");
                } else System.out.println("User has selected the current projects language.");
            } catch (Exception ex) {
                System.out.println("An error occurred while changing the projects language.");
            }
        });
        englishProjectsLang.setOnAction(e -> {
            try {
                if (!PreferencesManager.getProjectsLang().equals("en")) {
                    PreferencesManager.setProjectsLang("en");
                } else System.out.println("User has selected the current projects language.");
            } catch (Exception ex) {
                System.out.println("An error occurred while changing the projects language.");
            }
        });
        projectsLanguage.getItems().addAll(sysProjectsLang, spanishProjectsLang, englishProjectsLang);

        // ===== THEME SWITCHING ===== //
        MenuButton themeSwitch = GlobalUtils.menuButton("theme.switch");
        MenuItem darkTheme = GlobalUtils.menuItem("theme.dark");
        MenuItem lightTheme = GlobalUtils.menuItem("theme.light");
        darkTheme.setOnAction(e -> {
            try {
                if (!PreferencesManager.getTheme().equals("darkTheme")) {
                    // Update the theme selected in Preferences
                    PreferencesManager.setTheme("darkTheme");
                    // Then, call the method updateStyles() using the scene and the current theme
                    insUIMan.updateStyles(MainView.getScene());
                } else System.out.println("User has selected the current theme.");
            } catch (Exception ex) {
                System.out.println("An error occurred while changing the theme.");
            }
        });
        lightTheme.setOnAction(e -> {
            try {
                if (!PreferencesManager.getTheme().equals("lightTheme")) {
                    // Update the theme selected in Preferences
                    PreferencesManager.setTheme("lightTheme");
                    // Then, call the method updateStyles() using the scene and the current theme
                    insUIMan.updateStyles(MainView.getScene());
                } else System.out.println("User has selected the current theme.");
            } catch (Exception ex) {
                System.out.println("An error occurred while changing the theme.");
            }
        });
        themeSwitch.getItems().addAll(darkTheme, lightTheme);

        // ===== CLOSE SETTINGS ===== //
        Button closeSettings = GlobalUtils.closeButton(settingsPanel);
        // Set the content of the mainContent to null

        settingsPanel.getChildren().addAll(
            languages,
            projectsLanguage,
            themeSwitch,
            closeSettings
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
