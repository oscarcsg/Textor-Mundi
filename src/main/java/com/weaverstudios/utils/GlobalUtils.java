// com/weaverstudios/utils/GlobalUtils.java
package com.weaverstudios.utils;

import java.io.File;
import java.util.List;
import java.util.Optional;

import com.weaverstudios.main.LanguageManager;
import com.weaverstudios.main.MainView;
import com.weaverstudios.main.ProjectManager;

import javafx.animation.RotateTransition;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

/*
 * Utility class providing global UI components and methods for common tasks.
 * This class centralizes the creation of UI elements, ensuring consistency
 * and facilitating easy modifications to styles and behavior. Each global UI
 * element has its own ID for styling.
 */
public class GlobalUtils {
    // ====== SINGLETON ======
    private static GlobalUtils insGloUt = new GlobalUtils();
    private GlobalUtils (){} // This allows to create an instance of this class for calling methods reasons
    public static GlobalUtils getInstance() {
        return insGloUt;
    }

    /*
     * Displays an error alert dialog with a given title and message
     */
    public static void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(LanguageManager.getText(title));
        alert.setHeaderText(null); // No header text is set
        alert.setContentText(LanguageManager.getText(message));
        alert.showAndWait(); // Display the alert and wait for user acknowledgment
    }

    /*
     * Displays a confirmation dialog with a given title and message
     */
    public static ButtonType confirmationDialog(String title, String message, String decision1, String decision2) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(LanguageManager.getText(title));
        alert.setHeaderText(null);
        alert.setContentText(LanguageManager.getText(message));

        ButtonType button1 = new ButtonType(LanguageManager.getText(decision1));
        ButtonType button2 = new ButtonType(LanguageManager.getText(decision2));

        alert.getButtonTypes().setAll(button1, button2);

        Optional<ButtonType> decision = alert.showAndWait();
        return decision.orElse(ButtonType.CANCEL);
    }

    /*
     * Displays a choice dialog with as many options as programmer wants
     * 
     * First, create a String list with the options. Then, create a String variable calling this method
     * with the 3 Strings and the String list
     */
    public static <T> T choiceDialog(String titleKey, String headerKey, String contentKey, List<T> options) {
        if (options == null || options.isEmpty()) {
            throw new IllegalArgumentException("The options list cannot be empty or null.");
        }

        ChoiceDialog<T> dialog = new ChoiceDialog<>(options.get(0), options);
        dialog.setTitle(LanguageManager.getText(titleKey));
        dialog.setHeaderText(LanguageManager.getText(headerKey));
        dialog.setContentText(LanguageManager.getText(contentKey));

        Optional<T> result = dialog.showAndWait();
        return result.orElse(null); // Return the selected option or null if canceled
    }


    /*
     * Creates a close button that removes a given panel from the main container
     */
    public static Button closeButton(Node panel) {
        Button closeButton = new Button(LanguageManager.getText("function.close"));
        closeButton.setId("closeButton");
        closeButton.setOnAction(e -> MainView.getMainContainer().getChildren().remove(panel));
        return closeButton;
    }

    /*
     * Creates a cancel button that removes a given panel from the main container
     */
    public static Button cancelButton(Node panel) {
        Button cancelButton = new Button(LanguageManager.getText("function.cancel"));
        cancelButton.setId("cancelButton");
        cancelButton.setOnAction(e -> MainView.getMainContainer().getChildren().remove(panel));
        return cancelButton;
    }

    /*
     * Creates a save button without specific functionality
     */
    public static Button saveButton() {
        Button saveButton = new Button(LanguageManager.getText("function.save"));
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
     * Creates a Button with a graphic, mainly used in the sidePanel
     */
    public Button imgButton(boolean folderCreator, String imageName, String textKey, Runnable action) {
        String imgPath = "/com/weaverstudios/images/" + imageName;
        Button imgButton = new Button();

        ImageView imageView = new ImageView(new Image(getClass().getResource(imgPath).toExternalForm()));
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);

        // Create a VBox to contain the img and the button's name
        VBox vBox = new VBox(
            imageView,
            new Label(LanguageManager.getText(textKey))
        );
        vBox.setAlignment(Pos.CENTER);
        
        imgButton.setGraphic(vBox);

        if (folderCreator) {
            imgButton.setOnAction(e -> {
                if (action != null) {
                    ProjectManager.createSubFolder(LanguageManager.getProjectsCreationText(textKey));
                    if (ProjectManager.getFlag()) {
                        action.run();
                    }
                } else System.out.println("Not a valid action");
            });
        } else {
            imgButton.setOnAction(e -> {
                if (action != null) {
                    action.run();
                }
            });
        }

        return imgButton;
    }

    /*
     * Creates the tabPane with just horizontal scroll
     */
    public static HBox tabPane(File folderPath) {
        HBox tabPanePanel = new HBox();
        TabPane tabPane = new TabPane();

        // Ensure the TabPane has a minimum size
        tabPane.setMinWidth(200);
        tabPane.setMinHeight(40);

        File[] folder = folderPath.listFiles();

        for (File file : folder) {
            Tab tab = new Tab(file.getName());
            tabPane.getTabs().add(tab);
        }

        SVGPath reloadIconPath1 = new SVGPath();
        reloadIconPath1.setContent("M12,2a10.032,10.032,0,0,1,7.122,3H16a1,1,0,0,0-1,1h0a1,1,0,0,0,1,1h4.143A1.858,1.858,0,0,0,22,5.143V1a1,1,0,0,0-1-1h0a1,1,0,0,0-1,1V3.078A11.981,11.981,0,0,0,.05,10.9a1.007,1.007,0,0,0,1,1.1h0a.982.982,0,0,0,.989-.878A10.014,10.014,0,0,1,12,2Z");
        SVGPath reloadIconPath2 = new SVGPath();
        reloadIconPath2.setContent("M22.951,12a.982.982,0,0,0-.989.878A9.986,9.986,0,0,1,4.878,19H8a1,1,0,0,0,1-1H9a1,1,0,0,0-1-1H3.857A1.856,1.856,0,0,0,2,18.857V23a1,1,0,0,0,1,1H3a1,1,0,0,0,1-1V20.922A11.981,11.981,0,0,0,23.95,13.1a1.007,1.007,0,0,0-1-1.1Z");

        Group reloadIcon2 = new Group(reloadIconPath1, reloadIconPath2);

        Button reload = new Button();
        reload.setId("reload-button");
        reload.setGraphic(reloadIcon2); // Use StackPane as the graphic

        // Ensure the reload button has a fixed size
        reload.setPrefWidth(40);
        reload.setPrefHeight(40);

        // Add RotateTransition for the text
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), reloadIcon2);
        rotateTransition.setByAngle(360);

        reload.setOnMouseEntered(e -> rotateTransition.playFromStart());

        HBox.setHgrow(tabPane, Priority.ALWAYS);
        tabPanePanel.getChildren().addAll(tabPane, reload);

        return tabPanePanel;
    }
}
