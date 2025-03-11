// com/weaverstudios/main/PreferencesManager.java
package com.weaverstudios.main;

import java.io.File;
import java.util.prefs.Preferences;

/*
 * Class to manage user preferences like theme and language
 */
public class PreferencesManager {
    // Creating a static instance of Preferences associated with this class
    private static final Preferences prefs = Preferences.userNodeForPackage(PreferencesManager.class);

    // =======================
    //  KEYS FOR PREFERENCIES
    // =======================
    // Constant key for storing the selected theme
    private static final String THEME_KEY = "selectedTheme";
    // Constant key for storing the selected language
    private static final String LANGUAGE_KEY = "selectedLanguage";
    // Constant key for storing the selected projects creation language
    private static final String PROJECTS_LANG_KEY = "selectedProjectsLanguage";
    // Cosntant key for storing the default project path
    private static final String DEFAULT_PROJECT = "selectedDefaultProject";

    // ===== THEME ===== //
    // Method to set the user's preferred theme
    public static void setTheme(String theme) {
        // Storing the provided theme in preferences
        prefs.put(THEME_KEY, theme);
    }

    // Method to get the user's preferred theme
    public static String getTheme() {
        // Retrieving the theme from preferences, with a default value if not set
        return prefs.get(THEME_KEY, "lightTheme");
    }

    // ===== LANGUAGE ===== //
    // Method to set the user's preferred lang
    public static void setLanguage(String language) {
        // Storing the provided lang in preferences
        prefs.put(LANGUAGE_KEY, language);
    }

    // Method to get the user's preferred lang
    public static String getLanguage() {
        // Retrieving the lang from preferences, with a default value if not set
        return prefs.get(LANGUAGE_KEY, LanguageManager.getSystemLang());
    }

    // ===== PROJECTS LANGUAGE ===== //
    // Method to set the user's preferred lang to projects creation
    public static void setProjectsLang(String language) {
        prefs.put(PROJECTS_LANG_KEY, language);
    }

    // Method to get the user's preferred lang to projects creation
    public static String getProjectsLang() {
        return prefs.get(PROJECTS_LANG_KEY, LanguageManager.getSystemLang());
    }

    // ===== PROJECTS PREFERENCES ===== //
    // Method to set the user preferred default project
    public static void setDefaultProject(File project) {
        if (project != null) {
            prefs.put(DEFAULT_PROJECT, project.getAbsolutePath());
        }
    }
    // Method to get the user preferred default project
    public static File getDefaultProject() {
        String path = prefs.get(DEFAULT_PROJECT, null);
        return (path != null) ? new File(path) : null;
    } 

    // =========================
    //    RESTART PREFERENCES
    // =========================
    // Method to reset user preferences by removing stored keys
    public static void restartPreferences() {
        // Removing the stored theme key from preferences
        prefs.remove(THEME_KEY);
        // Removing the stored language key from preferences
        prefs.remove(LANGUAGE_KEY);
        // Removing the stored language key from preferences for projects creation
        prefs.remove(PROJECTS_LANG_KEY);
        // Removing the stored default project path
        prefs.remove(null);
    }
}
