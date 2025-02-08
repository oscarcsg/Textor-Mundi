//com/weaverstudios/main/MainView.java
package com.weaverstudios.main;

import java.util.Locale;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainView {

    private static StackPane root; // Main container (overlay)
    private static BorderPane mainContent; // Structure with menu and main view
    private static MenuBar menuBar;
    private static Locale locale;

    // =====================================
    //         Main View creation
    // =====================================
    public void show(Stage primaryStage) {
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
        Scene scene = new Scene(root, screenWidth, screenHeight);

        primaryStage.setTitle("Textor Mundi");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true); // Initialize maximazed windows
        primaryStage.show();
    }

    // Method to change the content of the StackPane
    public void setView(Node view) {
        mainContent.setCenter(view);
    }
    // Asign the actual locale to 'locale' variable
    public Locale setCurrentLocale() {
        return LanguageManager.getCurrentLocale();
    }



    // =======================================
    //   MenuBar (File, View, Settings, etc)
    // =======================================
    private MenuBar createMenuBar() {
        menuBar = new MenuBar();

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

        settings.setOnAction(e -> {
            VBox settingsPanel = MenuActions.settingsAction();
            setView(settingsPanel);
        });

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



    // =========================================
    //      Operations to update language
    // =========================================
    public static void setLocale(Locale newLocale){
        locale = newLocale;
    }
    public static Locale getLocale(){
        return locale;
    }

    public static void update() {
        // Update element's texts when necesary
        updateLanguage(mainContent);
        // If u've more elements with text, add another updateX() operation
    }

    private static void updateLanguage(Node node) {
        if (node == null) return;

        // If node is a text (Label, Button, CheckBox, etc.)
        if (node instanceof Labeled labeledNode) updateLabeled(labeledNode);
        // If node is a MenuBar
        if (node instanceof MenuBar menuBar) updateMenuBarItems(menuBar);
        // If node is a MenuButton
        if (node instanceof MenuButton menuButton) updateMenuButtonItems(menuButton);
        // If node is a container (VBox, HBox, GridPane, Pane, etc.), recoursive call updateLanguage() to update its sons
        if (node instanceof Parent parentNode) {
            for (Node child : parentNode.getChildrenUnmodifiable()) {
                updateLanguage(child);
            }
        }
    }

    private static void updateLabeled(Labeled labeledNode) {
        String key = LanguageManager.getKey(labeledNode.getText(), getLocale());
        if (key != null) {
            labeledNode.setText(LanguageManager.getText(key));
        }
    }
        
    private static void updateMenuBarItems(MenuBar menuBar) {
        // Update text of the MenuBar
        for (Menu menu : menuBar.getMenus()) {
            String text = menu.getText();  // We set variable 'text' as the text content of the Menu
            String key = LanguageManager.getKey(text, getLocale()); // Obtain the key of that text
            if (key != null) {
                menu.setText(LanguageManager.getText(key)); // Update Menu text using key if exists
            }
            // Update texts of MenuItems (MenuBar)
            for (MenuItem menuItem : menu.getItems()) {
                text = menuItem.getText();  // We set variable 'text' as the text content of the MenuItem
                key = LanguageManager.getKey(text, getLocale()); // Obtain the key of that text
                if (key != null) {
                    menuItem.setText(LanguageManager.getText(key)); // Update MenuItem text using key if key exists
                }
            }
        }
    }

    private static void updateMenuButtonItems(MenuButton menuButton) {
        // Update text of the MenuButton
        String text = menuButton.getText(); // We set variable 'text' as the text content of the MenuButton
        String key = LanguageManager.getKey(text, getLocale()); // Obtain the key of that text
        if (key != null) {
            menuButton.setText(LanguageManager.getText(key)); // Update MenuButton text using key if exists
        }
        // Update texts of MenuItems (MenuButton)
        for (MenuItem menuItem : menuButton.getItems()) {
            text = menuItem.getText(); // We set variable 'text' as the text content of the MenuItem
            key = LanguageManager.getKey(text, getLocale()); // Obtain the key of that text
            if (key != null) {
                menuItem.setText(LanguageManager.getText(key)); // Update MenuItem text using key if key exists
            }
        }
    }
}