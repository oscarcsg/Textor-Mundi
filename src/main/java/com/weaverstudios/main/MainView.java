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

        // =======================
        //       File menu
        // =======================
        Menu menuFile = new Menu(LanguageManager.getText("file.menu"));
        MenuItem create = new MenuItem(LanguageManager.getText("file.create"));
        MenuItem open = new MenuItem(LanguageManager.getText("file.open"));
        MenuItem save = new MenuItem(LanguageManager.getText("file.save"));
        MenuItem saveAs = new MenuItem(LanguageManager.getText("file.saveAs"));
        MenuItem close = new MenuItem(LanguageManager.getText("file.close"));
        MenuItem exit = new MenuItem(LanguageManager.getText("file.exit"));

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
        Menu menuEdit = new Menu(LanguageManager.getText("edit.menu"));
        MenuItem copy = new MenuItem(LanguageManager.getText("edit.copy"));
        MenuItem cut = new MenuItem(LanguageManager.getText("edit.cut"));
        MenuItem paste = new MenuItem(LanguageManager.getText("edit.paste"));
        MenuItem selectAll = new MenuItem(LanguageManager.getText("edit.selectAll"));

        copy.setOnAction(e -> MenuActions.copyAction());
        cut.setOnAction(e -> MenuActions.cutAction());
        paste.setOnAction(e -> MenuActions.pasteAction());
        selectAll.setOnAction(e -> MenuActions.selectAllAction());

        menuEdit.getItems().addAll(copy, cut, paste, selectAll);


        // =======================
        //       View menu
        // =======================
        Menu menuView = new Menu(LanguageManager.getText("view.menu"));
        MenuItem fullscreen = new MenuItem(LanguageManager.getText("view.fullscreen"));
        MenuItem zoomIn = new MenuItem(LanguageManager.getText("view.zoomIn"));
        MenuItem zoomOut = new MenuItem(LanguageManager.getText("view.zoomOut"));

        fullscreen.setOnAction(e -> MenuActions.fullscreenAction());
        zoomIn.setOnAction(e -> MenuActions.zoomInAction());
        zoomOut.setOnAction(e -> MenuActions.zoomOutAction());

        menuView.getItems().addAll(fullscreen, zoomIn, zoomOut);


        // =======================
        //       Tools menu
        // =======================
        Menu menuTools = new Menu(LanguageManager.getText("tools.menu"));
        MenuItem settings = new MenuItem(LanguageManager.getText("tools.settings"));

        settings.setOnAction(e -> MenuActions.settingsAction());

        menuTools.getItems().addAll(settings);


        // =======================
        //       Help menu
        // =======================
        Menu menuHelp = new Menu(LanguageManager.getText("help.menu"));
        MenuItem documentation = new MenuItem(LanguageManager.getText("help.documentation"));
        MenuItem KBshortcuts = new MenuItem(LanguageManager.getText("help.KBshortcuts"));
        MenuItem about = new MenuItem(LanguageManager.getText("help.about"));
        MenuItem reportBug = new MenuItem(LanguageManager.getText("help.reportBug"));

        documentation.setOnAction(e -> MenuActions.documentationAction());
        KBshortcuts.setOnAction(e -> MenuActions.KBshortcutsAction());
        about.setOnAction(e -> MenuActions.aboutAction());
        reportBug.setOnAction(e -> MenuActions.reportBugAction());

        menuHelp.getItems().addAll(documentation, KBshortcuts, about, reportBug);


        menuBar.getMenus().addAll(menuFile, menuEdit, menuView, menuTools, menuHelp);
        return menuBar;
    }
}