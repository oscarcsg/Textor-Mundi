// com/weaverstudios/utils/GlobalUtils.java
package com.weaverstudios.utils;

import com.weaverstudios.main.LanguageManager;
import com.weaverstudios.main.MainView;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/*
 * Utility class providing global UI components and methods for common tasks.
 * This class centralizes the creation of UI elements, ensuring consistency
 * and facilitating easy modifications to styles and behavior. Each global UI
 * element has its own ID for styling.
 */
public class GlobalUtils {
    /*
     * Displays an error alert dialog with a given title and message
     */
    public static void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header text is set
        alert.setContentText(message);
        alert.showAndWait(); // Display the alert and wait for user acknowledgment
    }


    /*
     * Creates a close button that removes a given panel from the main container
     */
    public static Button closeButton(Node panel) {
        Button closeButton = new Button(LanguageManager.getText("button.close"));
        closeButton.setId("closeButton");
        closeButton.setOnAction(e -> MainView.getMainContainer().getChildren().remove(panel));
        return closeButton;
    }

    /*
     * Creates a cancel button that removes a given panel from the main container
     */
    public static Button cancelButton(Node panel) {
        Button cancelButton = new Button(LanguageManager.getText("button.cancel"));
        cancelButton.setId("cancelButton");
        cancelButton.setOnAction(e -> MainView.getMainContainer().getChildren().remove(panel));
        return cancelButton;
    }

    /*
     * Creates a save button without specific functionality
     */
    public static Button saveButton() {
        Button saveButton = new Button(LanguageManager.getText("button.save"));
        saveButton.setId("saveButton");
        return saveButton;
    }

    /*
     * Creates a Menu object with a text label retrieved from language settings
     */
    public static Menu menu(String langKey) {
        Menu menu = new Menu(LanguageManager.getText(langKey));
        menu.getStyleClass().add("menu");
        return menu;
    }

    /*
     * Creates a MenuButton object with a text label retrieved from language settings
     */
    public static MenuButton menuButton(String langKey) {
        MenuButton menuButton = new MenuButton(LanguageManager.getText(langKey));
        menuButton.setId("menuButton");
        return menuButton;
    }

    /*
     * Creates a MenuItem object with a text label retrieved from language settings
     */
    public static MenuItem menuItem(String langKey) {
        MenuItem menuItem = new MenuItem(LanguageManager.getText(langKey));
        menuItem.getStyleClass().add("menuItem");
        return menuItem;
    }

    /*
     * Creates a VBox (Vertical Box) container with a predefined style
     */
    public static VBox vBox() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("VBox");
        return vBox;
    }

    /*
     * Creates an HBox (Horizontal Box) container with a predefined style
     */
    public static HBox hBox() {
        HBox hBox = new HBox();
        hBox.getStyleClass().add("HBox");
        return hBox;
    }
}
