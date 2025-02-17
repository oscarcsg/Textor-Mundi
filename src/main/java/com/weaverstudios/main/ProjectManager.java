package com.weaverstudios.main;

import java.io.File;

public class ProjectManager {
    private static File currentProjectFolder = null; // This stores the selected project folder

    public static void setCurrentProjectFolder(File selectedFolder) {
        currentProjectFolder = selectedFolder;
    }

    public static File getCurrentProjectFolder() {
        return currentProjectFolder;
    }

    public static void loadProjectFiles(File projectFolder) {
        // Retrieve all files from the specified folder
        File[] files = projectFolder.listFiles();
    
        // Check if the folser is empty or cannot be accessed
        if (files == null || files.length == 0) {
            System.out.println("The selected project folder is empty.");
            return;
        }
    
        // Print the detected files in the project folder
        System.out.println("Files detected in the project folder:");
        for (File file : files) {
            if (file.isFile()) { // Ensure it's a file and not a subdirectory
                String fileName = file.getName();
                String extension = "";
    
                // Extract file extension, if available
                int lastDotIndex = fileName.lastIndexOf(".");
                if (lastDotIndex > 0) {
                    extension = fileName.substring(lastDotIndex + 1);
                }
    
                // Display file name and its extension in console
                System.out.println("- " + fileName + " (Extension: " + (extension.isEmpty() ? "None" : extension) + ")");
            }
        }
    }
    
}
