// com/weaverstudios/main/MainView.java
package com.weaverstudios.main;

import java.util.Locale;

import com.weaverstudios.timeLine.ui.TimeLineMV;
import com.weaverstudios.utils.GlobalUtils;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
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

/*
 * MainView is the primary user interface of the app
 * It manages the main layout, menu bar, and views displayed in the app
 * Uses Singleton pattern to ensure only one instance is created
 */
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
        // Charge theme styles sheet
        UIManager.getInstance().updateStyles(scene);

        primaryStage.setTitle("Textor Mundi");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true); // Initialize maximazed windows
        primaryStage.show();
        this.primaryStage = primaryStage;
    }

    public static void setDefaultMainView(){
        mainContent.setCenter(defContent());
    }

    // Returns the app's primary stage
    public Stage getStage(){
        return primaryStage;
    }

    // Changes the mainContent (center)
    public static void setMainContent(Node view) {
        mainContent.setCenter(view);
    }

    // Adds a new view (overlay) to the main container
    public static void setOverlayView(Node view) {
        if (!mainContainer.getChildren().contains(view)) {
            mainContainer.getChildren().add(view);
        }
    }
    // Removes a view from the main container
    public void removeOverlayView(Node view) {
        mainContainer.getChildren().remove(view);
    }    

    // Returns the main content layout
    public static BorderPane getMainContent() {
        return mainContent;
    }

    // Returns the main container (StackPane) for overlays
    public static StackPane getMainContainer() {
        return mainContainer;
    }

    // Asign the current locale to 'locale' variable
    public Locale setCurrentLocale() {
        return LanguageManager.getCurrentLocale();
    }

    // Returns the current application scene
    public static Scene getScene() {
        // Scene currentScene = scene;
        return scene;
    }


    // =======================================
    //      Sub App Selection Side Panel
    // =======================================
    private GlobalUtils insGloUt = GlobalUtils.getInstance();
    private VBox createSubAppSelectionPanel() {
        VBox subAppSelectionPanel = new VBox();
        subAppSelectionPanel.getStyleClass().add("sidePanel");

        subAppSelectionPanel.getChildren().addAll(
            insGloUt.imgButton(
                true,
                "Cronos.png",
                "sidePanel.button.timeLine",
                () -> TimeLineMV.timeLineMainView()
            )
        );

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
            UIManager.getInstance().updateStyles(settingsPanel);
            setOverlayView(settingsPanel);
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
    // Sets the application's locale
    public static void setLocale(Locale newLocale){
        locale = newLocale;
    }
    // Returns the currently set locale
    public static Locale getLocale(){
        return locale;
    }


    // =========================================
    //    Default content of the mainContent
    // =========================================
    private static VBox defContent() {
        // Create the main container (VBox) to hold all elements
        VBox defContent = new VBox();
        defContent.setAlignment(Pos.TOP_CENTER); // Align content at the top center
        defContent.setPrefHeight(mainContent.getHeight()); // Set height to match the entire mainContent space

        // Create the welcome text label
        Label welcome = new Label(LanguageManager.getText("label.defContent.welcome"));

        // Container to divide the two information columns
        HBox colsContainer = new HBox(10); // Horizontal box with 10px spacing between children
        colsContainer.setAlignment(Pos.CENTER); // Center-align the columns


        // ===== Left Column - How to Start =====

        // Container for the left column
        VBox leftCol = new VBox();
        leftCol.setAlignment(Pos.CENTER_LEFT); // Align content to the left

        // Container to hold the "How to Start" information
        HBox leftColHTSContainer = new HBox();
        leftColHTSContainer.setAlignment(Pos.CENTER_LEFT); // Align content to the left

        // VBox to organize the step-by-step instructions
        VBox hTsInfo = new VBox();
        hTsInfo.setAlignment(Pos.CENTER_LEFT); // Align content to the left

        // Labels for step-by-step instructions
        Label howToStart = new Label(LanguageManager.getText("label.leftCol.howToStart"));
        Label firstStep = new Label(LanguageManager.getText("label.leftCol.firstStep"));
        Label secondStep = new Label(LanguageManager.getText("label.leftCol.secondStep"));
        Label thirdStep = new Label(LanguageManager.getText("label.leftCol.thirdStep"));

        // Add all step labels to the VBox
        hTsInfo.getChildren().addAll(
            howToStart,
            firstStep,
            secondStep,
            thirdStep
        );

        // Spacer to push the content to the right
        Region spacer = new Region();
        spacer.setMaxWidth(150); // Maximum width for spacing
        HBox.setHgrow(spacer, Priority.ALWAYS); // Allow it to grow to fill space

        // Add spacer and step information container to the left column container
        leftColHTSContainer.getChildren().addAll(
            spacer,
            hTsInfo
        );

        // Add the left column container to the main left column VBox
        leftCol.getChildren().addAll(
            leftColHTSContainer
        );


        // ===== Right Column - Additional Info =====

        // Container for the right column
        VBox rightCol = new VBox();
        rightCol.setAlignment(Pos.CENTER); // Center-align content

        // Add a label for the "Developing" section
        rightCol.getChildren().add(new Label(LanguageManager.getText("label.rightCol.developing")));

        // Add both left and right columns to the main container
        colsContainer.getChildren().addAll(leftCol, rightCol);

        // Allow columns to expand and fill the available space
        VBox.setVgrow(colsContainer, Priority.ALWAYS);
        HBox.setHgrow(leftCol, Priority.ALWAYS);
        HBox.setHgrow(rightCol, Priority.ALWAYS);

        // Add welcome label and columns container to the main VBox
        defContent.getChildren().addAll(
            welcome,
            colsContainer
        );

        return defContent;
    }
}
