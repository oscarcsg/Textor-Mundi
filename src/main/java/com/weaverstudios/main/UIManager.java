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

/*
 * Class to manage the user interface elements, including lang and styles updates
 */
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

    /*
     * Method to update the language of the UI elements at launch
     */
    public static void updateLanguageLaunch() {
        // Update element's texts when necesary
        updateLanguage(MainView.getMainContent());
        // If u've more elements with text, add another updateX() operation
    }

    /*
     * Recursive method to update the language of a given Node
     */
    private static void updateLanguage(Node node) {
        if (node == null) return; // If node is null, return early

        // If node is a text (Label, Button, CheckBox, etc.), update its items
        if (node instanceof Labeled labeledNode) updateLabeled(labeledNode);
        // If node is a MenuBar, update its items
        if (node instanceof MenuBar menuBar) updateMenuBarItems(menuBar);
        // If node is a MenuButton, update its items
        if (node instanceof MenuButton menuButton) updateMenuButtonItems(menuButton);
        // If node is a container (VBox, HBox, GridPane, Pane, etc.),
        // recoursive call updateLanguage() to update its sons
        if (node instanceof Parent parentNode) {
            for (Node child : parentNode.getChildrenUnmodifiable()) {
                updateLanguage(child); // Update each child node
            }
        }
    }

    /*
     * Method to update the text of labeled nodes
     */
    private static void updateLabeled(Labeled labeledNode) {
        // Get the key for the current text based on locale
        String key = LanguageManager.getKey(labeledNode.getText(), MainView.getLocale());
        if (key != null) {
            // If a key exists, update the labeled node's text
            labeledNode.setText(LanguageManager.getText(key));
        }
    }
    
    /*
     * Method to update the text of MenuBar items
     */
    private static void updateMenuBarItems(MenuBar menuBar) {
        // Update text of the MenuBar
        for (Menu menu : menuBar.getMenus()) {
            String text = menu.getText();  // Set variable 'text' as the text content of the Menu
            String key = LanguageManager.getKey(text, MainView.getLocale()); // Obtain the key of that text
            if (key != null) {
                // Update Menu text using key if exists
                menu.setText(LanguageManager.getText(key));
            }
            // Update texts of MenuItems (MenuBar)
            for (MenuItem menuItem : menu.getItems()) {
                text = menuItem.getText();  // Set variable 'text' as the text content of the MenuItem
                key = LanguageManager.getKey(text, MainView.getLocale()); // Obtain the key of that text
                if (key != null) {
                    // Update MenuItem text using key if key exists
                    menuItem.setText(LanguageManager.getText(key));
                }
            }
        }
    }

    /*
     * Method to update the text of MenuButton items
     */
    private static void updateMenuButtonItems(MenuButton menuButton) {
        // Update text of the MenuButton
        String text = menuButton.getText(); // Set variable 'text' as the text content of the MenuButton
        String key = LanguageManager.getKey(text, MainView.getLocale()); // Obtain the key of that text
        if (key != null) {
            // Update MenuButton text using key if exists
            menuButton.setText(LanguageManager.getText(key));
        }
        // Update texts of MenuItems (MenuButton)
        for (MenuItem menuItem : menuButton.getItems()) {
            text = menuItem.getText(); // Set variable 'text' as the text content of the MenuItem
            key = LanguageManager.getKey(text, MainView. getLocale()); // Obtain the key of that text
            if (key != null) {
                // Update MenuItem text using key if key exists
                menuItem.setText(LanguageManager.getText(key));
            }
        }
    }



    // =====================
    //     UPDATE STYLES // UIManager.java
    // =====================
    /* 
     * IMPORTANT: IT IS NEEDED AN INSTANCE OF THIS JAVA CLASS DECLARED ON THE JAVA CLASS
     *            THAT WANTS TO EXECUTE THESE METHODS TO CALL THEM. IF THERE IS NO INSTANCE,
     *            CALLING THE METHOD WILL FAIL. ON THE OTHER HAND, THESE METHODS COULD
     *            BE STATIC, BUT 'getClass()' WOULD NOT WORK
     */
    
    // Method to update stylesheets for a Scene
    public void updateStyles(Scene scene) {
        // Set the variable 'currentTheme'
        String currentTheme = "/com/weaverstudios/css/" + PreferencesManager.getTheme() + ".css";
        // Clear existing stylesheets
        scene.getStylesheets().clear();
        // Add the current theme stylesheet
        scene.getStylesheets().add(getClass().getResource(currentTheme).toExternalForm());
    }

    // Method to update stylesheets for a Parent
    public void updateStyles(Parent parent) {
        // Set the variable 'currentTheme'
        String currentTheme = "/com/weaverstudios/css/" + PreferencesManager.getTheme() + ".css";
        // Clear existing stylesheets
        parent.getStylesheets().clear();
        // Add the current theme stylesheet
        parent.getStylesheets().add(getClass().getResource(currentTheme).toExternalForm());
    }
}
