// com/weaverstudios/utils/GlobalUtils.java
package com.weaverstudios.utils;

import com.weaverstudios.main.LanguageManager;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class GlobalUtils {
    // Method to show an error alert
    public static void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /*
     *  CLASS INFORMATION:      In this class is where the different buttons and interfaces
     *          are builded. This is done here to asign a global ID to every of these, this
     *          ID can be changed to those specific buttons and interfaces that requires to
     *          apply any specific style ONLY to that one.
     */
    public static Button closeButton() {
        Button closeButton = new Button(LanguageManager.getText("button.close"));
        closeButton.setId("closeButton");
        return closeButton;
    }

    public static Button cancelButton() {
        Button cancelButton = new Button(LanguageManager.getText("button.cancel"));
        cancelButton.setId("cancelButton");
        return cancelButton;
    }

    public static Button saveButton() {
        Button saveButton = new Button(LanguageManager.getText("button.save"));
        saveButton.setId("saveButton");
        return saveButton;
    }

    public static Menu menu(String langKey) {
        Menu menu = new Menu(LanguageManager.getText(langKey));
        menu.getStyleClass().add("menu");
        return menu;
    }

    public static MenuButton menuButton(String langKey) {
        MenuButton menuButton = new MenuButton(LanguageManager.getText(langKey));
        menuButton.setId("menuButton");
        return menuButton;
    }

    public static MenuItem menuItem(String langKey) {
        MenuItem menuItem = new MenuItem(LanguageManager.getText(langKey));
        menuItem.getStyleClass().add("menuItem");
        return menuItem;
    }
}
