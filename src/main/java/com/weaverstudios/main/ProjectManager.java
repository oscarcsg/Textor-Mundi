package com.weaverstudios.main;

import java.io.File;

/*
 * Class to manage project files and the current project folder
 */
public class ProjectManager {
    // This variable stores the selected project folder as a File object
    private static File currentProjectFolder = null;

    // Method to set the current project folder
    public static void setCurrentProjectFolder(File selectedFolder) {
        // Assigning the provided folder to the currentProjectFolder variable
        currentProjectFolder = selectedFolder;
    }

    // Method to get the current project folder
    public static File getCurrentProjectFolder() {
        // Returning the current project folder
        return currentProjectFolder;
    }

    /*
     * Method to load project files from the specified project folder
     */
    public static void loadProjectFiles(File projectFolder) {
        // Retrieve all files from the specified folder
        File[] files = projectFolder.listFiles();
    
        // Check if the folser is empty or cannot be accessed
        if (files == null || files.length == 0) {
            System.out.println("The selected project folder is empty.");
            return; // Exit the method if no files are found
        }
    
        // Print the detected files in the project folder
        System.out.println("Files detected in the project folder:");
        for (File file : files) {
            // Ensure it's a file and not a subdirectory
            if (file.isFile()) {
                String fileName = file.getName(); // Get the name of the file
                String extension = ""; // Initialize a variable to hold the file extension
    
                // Extract file extension, if available
                int lastDotIndex = fileName.lastIndexOf(".");
                if (lastDotIndex > 0) {
                    // Substring from the last dot to get the file extension
                    extension = fileName.substring(lastDotIndex + 1);
                }
    
                // Display file name and its extension in console
                System.out.println("- " + fileName + " (Extension: " + (extension.isEmpty() ? "None" : extension) + ")");
            }
        }
    }
}
