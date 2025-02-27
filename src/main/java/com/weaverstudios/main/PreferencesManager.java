// com/weaverstudios/main/PreferencesManager.java
package com.weaverstudios.main;

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

    // =========================
    //    RESTART PREFERENCES
    // =========================
    // Method to reset user preferences by removing stored keys
    public static void restartPreferences() {
        // Removing the stored theme key from preferences
        prefs.remove(THEME_KEY);
        // Removing the stored language key from preferences
        prefs.remove(LANGUAGE_KEY);
    }
}
