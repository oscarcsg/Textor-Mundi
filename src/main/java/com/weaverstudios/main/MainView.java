// com/weaverstudios/main/MainView.java
package com.weaverstudios.main;

import java.util.Locale;

import com.weaverstudios.utils.GlobalUtils;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainView {
    private static StackPane root; // Main container (overlay)
    private static BorderPane mainContent; // Structure with menu and main view
    private static Scene scene;
    private static MenuBar menuBar;
    private static Locale locale;

    // =====================================
    //         Main View creation
    // =====================================
    public void show(Stage primaryStage) {
        // Charge loaded language
        String savedLanguage = PreferencesManager.getLanguage(); // Get loaded language
        LanguageManager.setLanguage(Locale.of(savedLanguage)); // Configure language

        // Update locale variable for later usage
        locale = setCurrentLocale();

        // Interface creation
        mainContent = new BorderPane();

        // Obtain screen size and resolution
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // Create topBar, MenuBar
        mainContent.setTop(createMenuBar());

        // Create StackPanel and add its content
        root = new StackPane(mainContent);

        // Create scene and show
        scene = new Scene(root, screenWidth, screenHeight);
        // First charge the global styles sheet
        scene.getStylesheets().add(getClass().getResource("/com/weaverstudios/css/global.css").toExternalForm());
        // Charge theme styles sheet
        UIManager.getInstance().updateStyles(scene, PreferencesManager.getTheme());
        // Then charge the specific MainView styles sheet
        scene.getStylesheets().add(getClass().getResource("/com/weaverstudios/css/MainView.css").toExternalForm());

        primaryStage.setTitle("Textor Mundi");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true); // Initialize maximazed windows
        primaryStage.show();
    }

    // Method to change the content of the StackPane
    public void setView(Node view) {
        mainContent.setCenter(view);
    }
    // Get the mainContent to update languages
    public static BorderPane getMainContent() {
        return mainContent;
    }
    // Asign the actual locale to 'locale' variable
    public Locale setCurrentLocale() {
        return LanguageManager.getCurrentLocale();
    }
    //
    public static Scene getScene() {
        Scene currentScene = scene;
        return currentScene;
    }



    // =======================================
    //   MenuBar (File, View, Settings, etc)
    // =======================================
    private MenuActions insMenAct = MenuActions.getInstance();
    private MenuBar createMenuBar() {
        menuBar = new MenuBar();
        menuBar.setId("menuBar"); // ID for css styling

        // =======================
        //       File menu
        // =======================
        Menu menuFile = GlobalUtils.menu("file.menu");
        MenuItem create = GlobalUtils.menuItem("file.create");
        MenuItem open = GlobalUtils.menuItem("file.open");
        MenuItem save = GlobalUtils.menuItem("file.save");
        MenuItem saveAs = GlobalUtils.menuItem("file.saveAs");
        MenuItem close = GlobalUtils.menuItem("file.close");
        MenuItem exit = GlobalUtils.menuItem("file.exit");

        // Asign actions to every MenuItem
        create.setOnAction(e -> MenuActions.createAction());
        open.setOnAction(e -> MenuActions.openAction());
        save.setOnAction(e -> MenuActions.saveAction());
        saveAs.setOnAction(e -> MenuActions.saveAsAction());
        close.setOnAction(e -> MenuActions.closeAction());
        exit.setOnAction(e -> System.exit(0));

        menuFile.getItems().addAll(create, open, save, saveAs, close, exit);

        // =======================
        //       Edit menu
        // =======================
        Menu menuEdit = GlobalUtils.menu("edit.menu");
        MenuItem copy = GlobalUtils.menuItem("edit.copy");
        MenuItem cut = GlobalUtils.menuItem("edit.cut");
        MenuItem paste = GlobalUtils.menuItem("edit.paste");
        MenuItem selectAll = GlobalUtils.menuItem("edit.selectAll");

        copy.setOnAction(e -> MenuActions.copyAction());
        cut.setOnAction(e -> MenuActions.cutAction());
        paste.setOnAction(e -> MenuActions.pasteAction());
        selectAll.setOnAction(e -> MenuActions.selectAllAction());

        menuEdit.getItems().addAll(copy, cut, paste, selectAll);

        // =======================
        //       View menu
        // =======================
        Menu menuView = GlobalUtils.menu("view.menu");
        MenuItem fullscreen = GlobalUtils.menuItem("view.fullscreen");
        MenuItem zoomIn = GlobalUtils.menuItem("view.zoomIn");
        MenuItem zoomOut = GlobalUtils.menuItem("view.zoomOut");

        fullscreen.setOnAction(e -> MenuActions.fullscreenAction());
        zoomIn.setOnAction(e -> MenuActions.zoomInAction());
        zoomOut.setOnAction(e -> MenuActions.zoomOutAction());

        menuView.getItems().addAll(fullscreen, zoomIn, zoomOut);

        // =======================
        //       Tools menu
        // =======================
        Menu menuTools = GlobalUtils.menu("tools.menu");
        MenuItem settings = GlobalUtils.menuItem("tools.settings");

        settings.setOnAction(e -> {
            VBox settingsPanel = insMenAct.settingsAction();
            UIManager.getInstance().updateStyles(settingsPanel, PreferencesManager.getTheme());
            setView(settingsPanel);
        });

        menuTools.getItems().addAll(settings);

        // =======================
        //       Help menu
        // =======================
        Menu menuHelp = GlobalUtils.menu("help.menu");
        MenuItem documentation = GlobalUtils.menuItem("help.documentation");
        MenuItem KBshortcuts = GlobalUtils.menuItem("help.KBshortcuts");
        MenuItem about = GlobalUtils.menuItem("help.about");
        MenuItem reportBug = GlobalUtils.menuItem("help.reportBug");

        documentation.setOnAction(e -> MenuActions.documentationAction());
        KBshortcuts.setOnAction(e -> MenuActions.KBshortcutsAction());
        about.setOnAction(e -> MenuActions.aboutAction());
        reportBug.setOnAction(e -> MenuActions.reportBugAction());

        menuHelp.getItems().addAll(documentation, KBshortcuts, about, reportBug);


        menuBar.getMenus().addAll(menuFile, menuEdit, menuView, menuTools, menuHelp);
        return menuBar;
    }



    // =========================================
    //      Operations to update language
    // =========================================
    public static void setLocale(Locale newLocale){
        locale = newLocale;
    }
    public static Locale getLocale(){
        return locale;
    }
}