// com/weaverstudios/main/UIManager.java
package com.weaverstudios.main;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class UIManager {
    // ====== SINGLETON ======
    private static UIManager insUIMan = new UIManager();
    private UIManager (){} // This allows to create an instance of this class for calling methods reasons
    public static UIManager getInstance() {
        return insUIMan;
    }

    // =======================
    //     UPDATE LANGUAGE
    // =======================
    public static void updateLanguageLaunch() {
        // Update element's texts when necesary
        updateLanguage(MainView.getMainContent());
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
        String key = LanguageManager.getKey(labeledNode.getText(), MainView.getLocale());
        if (key != null) {
            labeledNode.setText(LanguageManager.getText(key));
        }
    }
        
    private static void updateMenuBarItems(MenuBar menuBar) {
        // Update text of the MenuBar
        for (Menu menu : menuBar.getMenus()) {
            String text = menu.getText();  // We set variable 'text' as the text content of the Menu
            String key = LanguageManager.getKey(text, MainView.getLocale()); // Obtain the key of that text
            if (key != null) {
                menu.setText(LanguageManager.getText(key)); // Update Menu text using key if exists
            }
            // Update texts of MenuItems (MenuBar)
            for (MenuItem menuItem : menu.getItems()) {
                text = menuItem.getText();  // We set variable 'text' as the text content of the MenuItem
                key = LanguageManager.getKey(text, MainView.getLocale()); // Obtain the key of that text
                if (key != null) {
                    menuItem.setText(LanguageManager.getText(key)); // Update MenuItem text using key if key exists
                }
            }
        }
    }

    private static void updateMenuButtonItems(MenuButton menuButton) {
        // Update text of the MenuButton
        String text = menuButton.getText(); // We set variable 'text' as the text content of the MenuButton
        String key = LanguageManager.getKey(text, MainView.getLocale()); // Obtain the key of that text
        if (key != null) {
            menuButton.setText(LanguageManager.getText(key)); // Update MenuButton text using key if exists
        }
        // Update texts of MenuItems (MenuButton)
        for (MenuItem menuItem : menuButton.getItems()) {
            text = menuItem.getText(); // We set variable 'text' as the text content of the MenuItem
            key = LanguageManager.getKey(text, MainView. getLocale()); // Obtain the key of that text
            if (key != null) {
                menuItem.setText(LanguageManager.getText(key)); // Update MenuItem text using key if key exists
            }
        }
    }



    // =====================
    //     UPDATE STYLES  // UIManager.java
    // =====================
    /* IMPORTANT: IT IS NEEDED AN INSTANCE OF THIS JAVA CLASS DECLARED ON THE JAVA CLASS THAT WANTS TO EXECUTE THESE METHODS
     *            TO CALL THEM. IF THERE IS NO INSTANCE, CALLING THE METHOD WILL FAIL. ON THE OTHER HAND, THESE METHODS COULD
     *            BE STATIC, BUT 'getClass()' WOULDNT WORK */
    
    // CHANGE RUTES TO STYLES SHEETS: "mainRoot" + fileName.css
    public void updateStyles(Scene scene, String currentTheme) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource("/com/weaverstudios/css/global.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource(currentTheme).toExternalForm());
    }

    public void updateStyles(Scene scene, String currentTheme, String dedicatedStyleSheet) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource("/com/weaverstudios/css/global.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource(currentTheme).toExternalForm());
        scene.getStylesheets().add(getClass().getResource(dedicatedStyleSheet).toExternalForm());
    }

    public void updateStyles(Parent parent, String currentTheme) {
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getClass().getResource("/com/weaverstudios/css/global.css").toExternalForm());
        parent.getStylesheets().add(getClass().getResource(currentTheme).toExternalForm());
    }

    public void updateStyles(Parent parent, String currentTheme, String dedicatedStyleSheet) {
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getClass().getResource("/com/weaverstudios/css/global.css").toExternalForm());
        parent.getStylesheets().add(getClass().getResource(currentTheme).toExternalForm());
        parent.getStylesheets().add(getClass().getResource(dedicatedStyleSheet).toExternalForm());
    }
}