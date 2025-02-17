// com/weaverstudios/main/PreferencesManager.java
package com.weaverstudios.main;

import java.util.prefs.Preferences;

public class PreferencesManager {
    private static final Preferences prefs = Preferences.userNodeForPackage(PreferencesManager.class);

    // =======================
    //  KEYS FOR PREFERENCIES
    // =======================
    private static final String THEME_KEY = "selectedTheme";
    private static final String LANGUAGE_KEY = "selectedLanguage";

    // ===== THEME =====
    public static void setTheme(String theme) {
        prefs.put(THEME_KEY, theme);
    }

    public static String getTheme() {
        return prefs.get(THEME_KEY, "/com/weaverstudios/css/lightMode.css");
    }

    // ===== LANGUAGE =====
    public static void setLanguage(String language) {
        prefs.put(LANGUAGE_KEY, language);
    }

    public static String getLanguage() {
        return prefs.get(LANGUAGE_KEY, LanguageManager.getSystemLang());
    }

    // =========================
    //    RESTART PREFERENCES
    // =========================
    public static void restartPreferences() {
        prefs.remove(THEME_KEY);
        prefs.remove(LANGUAGE_KEY);
    }
}
