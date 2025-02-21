// com/weaverstudios/main/MainView.java
package com.weaverstudios.main;

import java.util.Locale;

import com.weaverstudios.utils.GlobalUtils;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainView {
    private Stage primaryStage;
    private static StackPane mainContainer; // Container that will allow over
    private static BorderPane mainContent; // Structure with menu and main view
    private static Scene scene;
    private static MenuBar menuBar;
    private static Locale locale;

    // ====== SINGLETON ======
    private static MainView insMainView = new MainView();
    private MainView (){} // This allows to create an instance of this class for calling methods reasons
    public static MainView getInstance() {
        return insMainView;
    }

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
        //System.out.println("width:" + screenWidth + "height: " + screenHeight);

        // Create MenuBar
        mainContent.setTop(createMenuBar());

        // Create subApp selection panel
        mainContent.setLeft(createSubAppSelectionPanel());

        mainContent.setCenter(defContent());

        mainContainer = new StackPane();
        mainContainer.getChildren().add(mainContent);

        // Create scene and show
        scene = new Scene(mainContainer, screenWidth, screenHeight);
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
        this.primaryStage = primaryStage;
    }

    // Method to get the Stage
    public Stage getStage(){
        Stage stage = primaryStage;
        return stage;
    }

    // Method to change the content of the StackPane
    public void setView(Node view) {
        if (!mainContainer.getChildren().contains(view)) {
            mainContainer.getChildren().add(view);
        }
    }
    public void removeView(Node view) {
        mainContainer.getChildren().remove(view);
    }    

    // Get the mainContent to update languages
    public static BorderPane getMainContent() {
        return mainContent;
    }
    public static StackPane getMainContainer() {
        return mainContainer;
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
    //      Sub App Selection Side Panel
    // =======================================
    private VBox createSubAppSelectionPanel() {
        VBox subAppSelectionPanel = new VBox();
        subAppSelectionPanel.getStyleClass().add("sidePanel");

        //subAppSelectionPanel.getChildren().add(new Label("·"));

        return subAppSelectionPanel;
    }


    // =======================================
    //   MenuBar (File, View, Settings, etc)
    // =======================================
    private MenuActions insMenAct = MenuActions.getInstance();
    private MenuBar createMenuBar() {
        menuBar = new MenuBar();
        menuBar.getStyleClass().add("menuBar"); // ID for css styling

        // =======================
        //       File menu
        // =======================
        Menu menuFile = GlobalUtils.menu("file.menu");
        MenuItem newProject = GlobalUtils.menuItem("file.newProject");
        MenuItem open = GlobalUtils.menuItem("file.open");
        MenuItem importFile = GlobalUtils.menuItem("file.import");
        MenuItem save = GlobalUtils.menuItem("file.save");
        MenuItem saveAs = GlobalUtils.menuItem("file.saveAs");
        MenuItem close = GlobalUtils.menuItem("file.close");
        MenuItem exit = GlobalUtils.menuItem("file.exit");

        // Asign actions to every MenuItem
        newProject.setOnAction(e -> MenuActions.newProjectAction(insMainView.getStage()));
        open.setOnAction(e -> MenuActions.openAction(insMainView.getStage()));
        importFile.setOnAction(e -> MenuActions.importFileAction()); 
        save.setOnAction(e -> MenuActions.saveAction());
        saveAs.setOnAction(e -> MenuActions.saveAsAction());
        close.setOnAction(e -> MenuActions.closeAction());
        exit.setOnAction(e -> System.exit(0));

        menuFile.getItems().addAll(newProject, open, importFile, save, saveAs, close, exit);

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
            mainContainer.setOnMouseClicked(event -> removeView(settingsPanel)); // Cierra al hacer clic afuera
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

        // =======================
        //      SidePanel Btn
        // =======================

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


    // =========================================
    //    Default content of the mainContent
    // =========================================
    private VBox defContent() {
        // Create main container
        VBox defContent = new VBox();
        defContent.setAlignment(Pos.TOP_CENTER);
        defContent.setPrefHeight(mainContent.getHeight()); // Set height to the entire mainContent space

        // Welcome text label
        Label welcome = new Label(LanguageManager.getText("label.defContent.welcome"));

        // Container to divide the two info columns
        HBox colsContainer = new HBox(10); // With 10 of spacing between its children 
        colsContainer.setAlignment(Pos.CENTER);


        // Container of the left-column info
        VBox leftCol = new VBox();
        leftCol.setAlignment(Pos.CENTER_LEFT);

        HBox leftColHTSContainer = new HBox();
        leftColHTSContainer.setAlignment(Pos.CENTER_LEFT);

        VBox hTsInfo = new VBox();
        hTsInfo.setAlignment(Pos.CENTER_LEFT);

        Label howToStart = new Label(LanguageManager.getText("label.leftCol.howToStart"));
        Label firstStep = new Label(LanguageManager.getText("label.leftCol.firstStep"));
        Label secondStep = new Label(LanguageManager.getText("label.leftCol.secondStep"));
        Label thirdStep = new Label(LanguageManager.getText("label.leftCol.thirdStep"));

        // Crear el botón
        Button myButton = new Button("Continuar");
        myButton.setOnAction(null);

        // Contenedor para el botón con un espacio flexible
        HBox buttonContainer = new HBox();
        Region spacer = new Region();
        spacer.setMaxWidth(20);
        HBox.setHgrow(spacer, Priority.ALWAYS); // Hace que el espacio empuje el botón hacia la derecha
        buttonContainer.getChildren().addAll(spacer, myButton);

        hTsInfo.getChildren().addAll(
            howToStart,
            firstStep,
            secondStep,
            buttonContainer,
            thirdStep
        );
        spacer.setMaxWidth(200);
        leftColHTSContainer.getChildren().addAll(
            spacer,
            hTsInfo
        );
        leftCol.getChildren().addAll(
            leftColHTSContainer
        );
        

        // Container of the right-column info
        VBox rightCol = new VBox();
        rightCol.setAlignment(Pos.CENTER);
        rightCol.getChildren().add(new Label(LanguageManager.getText("label.rightCol.developing")));

        colsContainer.getChildren().addAll(leftCol, rightCol);


        VBox.setVgrow(colsContainer, Priority.ALWAYS);
        HBox.setHgrow(leftCol, Priority.ALWAYS);
        HBox.setHgrow(rightCol, Priority.ALWAYS);
        defContent.getChildren().addAll(
            welcome,
            colsContainer
        );
        return defContent;
    }
}