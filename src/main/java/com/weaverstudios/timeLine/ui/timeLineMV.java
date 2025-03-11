package com.weaverstudios.timeLine.ui;

import com.weaverstudios.main.ProjectManager;
import com.weaverstudios.utils.GlobalUtils;

import javafx.scene.layout.BorderPane;

public class TimeLineMV {
    private static BorderPane timeLineLayout;
    public static BorderPane timeLineMainView() {
        timeLineLayout = new BorderPane();

        timeLineLayout.setTop(GlobalUtils.tabPane(ProjectManager.getSpecificFolderPath("sidePanel.button.timeLine")));

        return timeLineLayout;
    }
}
