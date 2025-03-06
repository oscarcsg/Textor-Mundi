package com.weaverstudios.timeLine.ui;

import javafx.scene.layout.BorderPane;

public class TimeLineMV {
    private static BorderPane timeLineLayout;
    public static BorderPane timeLineMainView() {
        timeLineLayout = new BorderPane();

        return timeLineLayout;
    }
}
