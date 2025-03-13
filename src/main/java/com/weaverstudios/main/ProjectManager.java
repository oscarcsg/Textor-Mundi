// com/weaverstudios/main/ProjectManager.java
package com.weaverstudios.main;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.weaverstudios.utils.GlobalUtils;

/*
 * Class to manage project files and the current project folder
 */
public class ProjectManager {
    // Boolean flag to allow finishing or not the rest of actions
    private static boolean flag = true;
    public static boolean getFlag() {return flag;}

    // This variable stores the selected project folder as a File object
    private static File currentProjectFolder;
    private static MainView insMainView = MainView.getInstance();

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
     * Method to get the path of a specific folder
     */
    public static File getSpecificFolderPath(String textKey) {
        // Retrieve all files from the specified folder
        File[] projectFolder = getCurrentProjectFolder().listFiles();

        if (projectFolder != null && projectFolder.length != 0) {
            for (File file : projectFolder) {
                if (file.isDirectory()) {
                    String fileTextKey = LanguageManager.getKey(file.getName(), Locale.of(PreferencesManager.getProjectsLang()));
                    if (fileTextKey == null) {
                        for (Locale locale : LanguageManager.getLanguagesAvailable()) {
                            fileTextKey = LanguageManager.getKey(file.getName(), locale);
                            if (textKey.equals(fileTextKey)) {
                                return file;
                            }
                        }
                    } else if (textKey.equals(fileTextKey)) {
                        return file;
                    }
                }
            }
        } else System.out.println("The folder doesn't exists or is empty.");

        System.out.println("No matching folder found.");
        return null;
    }

    /*
     * Method to load project files from the specified project folder  //  THIS DOESN'T ACTUALLY WORKS IN THE APP, IT JUST
     * PRINT THE CONTENT OF THE PROJECT FOLDER ON CONSOLE
     */
    public static void loadProjectFiles(File projectFolder) {
        // Retrieve all files from the specified folder
        File[] files = projectFolder.listFiles();
    
        // Check if the folder is empty or cannot be accessed
        if (files == null || files.length == 0) {
            System.out.println("The selected project folder is empty.");
            return; // Exit the method if no files are found
        }
    
        // Print the detected files in the project folder
        System.out.println("Files detected in the project folder:");
        for (File file : files) {
            // Ensure it's a file and not a subdirectory
            if (file.isFile() || file.isDirectory()) {
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

    /*
     * Method to create subApps folders
     */
    public static void createSubFolder(String folderName) {
        if (getCurrentProjectFolder() == null) {
            List<String> options = Arrays.asList(
                LanguageManager.getText("file.newProject"),
                LanguageManager.getText("file.open")
            );
            String selected = GlobalUtils.choiceDialog(
                "choiceDialog.title",
                "choiceDialog.header",
                "choiceDialog.content",
                options
            );
            
            if (selected != null) {
                flag = true;
                if (selected.equals(LanguageManager.getText("file.newProject"))) {
                    MenuActions.newProjectAction(insMainView.getStage());
                } else MenuActions.openAction(insMainView.getStage());
    
                if (getCurrentProjectFolder() != null) {
                    File subFolder = new File(getCurrentProjectFolder(), folderName);
                    String folderNameKey = LanguageManager.getKey(folderName, Locale.of(PreferencesManager.getProjectsLang()));

                    boolean folderExists = searchSameFolders(folderNameKey);
                    
                    if (!folderExists) {
                        if (subFolder.mkdir()) {
                            System.out.println("Folder '" + folderName + "' created at: " + subFolder.getAbsolutePath());
                        }
                    } else System.out.println("This subfolder already exists.");

                    // MOSTRAR MENSAJE AL USUARIO PARA QUE SEPA QUE TENGA EL PROJECTS LANG CAMBIADO

                } else System.out.println("Project folder is still null after attempting to create a new project.");
            } else flag = false;

        } else if (getCurrentProjectFolder() != null) {
            flag = true;
            File subFolder = new File(getCurrentProjectFolder(), folderName);
            String folderNameKey = LanguageManager.getKey(folderName, Locale.of(PreferencesManager.getProjectsLang()));
            
            boolean folderExists = searchSameFolders(folderNameKey);
            
            if (!folderExists) {
                if (subFolder.mkdir()) {
                    System.out.println("Folder '" + folderName + "' created at: " + subFolder.getAbsolutePath());
                }
            } else System.out.println("This subfolder already exists.");

            // MOSTRAR MENSAJE AL USUARIO PARA QUE SEPA QUE TENGA EL PROJECTS LANG CAMBIADO

        } else System.out.println("Project folder is still null after attempting to create a new project.");
    }
    private static boolean searchSameFolders(String folderNameKey){
        if (getCurrentProjectFolder() == null || getCurrentProjectFolder().listFiles() == null) {
            return false;
        }
        for (File file : getCurrentProjectFolder().listFiles()) {
            String fileKey = LanguageManager.getKeyFromUnknown(file.getName());

            if (fileKey != null && fileKey.equals(folderNameKey)) {
                return true;
            }
        }
        return false;
    }
}
