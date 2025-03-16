// com/weaverstudios/timeLine/ui/TimeLineMV.java
package com.weaverstudios.timeLine.ui;

import com.weaverstudios.main.ProjectManager;
import com.weaverstudios.utils.GlobalUtils;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class TimeLineMV {
    // Structure with menu and main view
    private static BorderPane timeLineLayout;
    // Allow overlay content in the center of timeLineLayout
    private static StackPane timeLineStack;

    private GlobalUtils insGloUt = GlobalUtils.getInstance();

    // ====== SINGLETON ======
    private static TimeLineMV insTimeLine = new TimeLineMV();
    private TimeLineMV (){} // This allows to create an instance of this class for calling methods reasons
    public static TimeLineMV getInstance() {
        return insTimeLine;
    }


    // ================================
    //    TimeLine MainView creator
    // ================================
    public BorderPane timeLineMainView() {
        timeLineLayout = new BorderPane();
        timeLineStack = new StackPane();

        timeLineLayout.setTop(GlobalUtils.tabPane(ProjectManager.getSpecificFolderPath("sidePanel.button.timeLine")));
        timeLineLayout.setRight(createEventsPanel());
        timeLineLayout.setCenter(timeLineStack);
        return timeLineLayout;
    }


    private VBox createEventsPanel() {
        VBox eventsPanel = new VBox(10);
        eventsPanel.setAlignment(Pos.CENTER);
        eventsPanel.getStyleClass().add("sidePanel");
        eventsPanel.getChildren().addAll(
            insGloUt.imgButton(
                false,
                "Cronos.png",
                "timeLine.event.battle.title",
                () -> timeLineStack.getChildren().add(new Label("Prueba"))
            ),
            insGloUt.imgButton(
                false,
                "Cronos.png",
                "timeLine.event.battle.title",
                () -> timeLineStack.getChildren().add(new Label("Prueba"))
            ),
            insGloUt.imgButton(
                false,
                "Cronos.png",
                "timeLine.event.battle.title",
                () -> timeLineStack.getChildren().add(new Label("Prueba"))
            ),
            insGloUt.imgButton(
                false,
                "Cronos.png",
                "timeLine.event.battle.title",
                () -> timeLineStack.getChildren().add(new Label("Prueba"))
            ),
            insGloUt.imgButton(
                false,
                "Cronos.png",
                "timeLine.event.battle.title",
                () -> timeLineStack.getChildren().add(new Label("Prueba"))
            ),
            insGloUt.imgButton(
                false,
                "Cronos.png",
                "timeLine.event.battle.title",
                () -> timeLineStack.getChildren().add(new Label("Prueba"))
            ),
            insGloUt.imgButton(
                false,
                "Cronos.png",
                "timeLine.event.battle.title",
                () -> timeLineStack.getChildren().add(new Label("Prueba"))
            ),
            insGloUt.imgButton(
                false,
                "Cronos.png",
                "timeLine.event.battle.title",
                () -> timeLineStack.getChildren().add(new Label("Prueba"))
            )
        );

        return eventsPanel;
    }
}
