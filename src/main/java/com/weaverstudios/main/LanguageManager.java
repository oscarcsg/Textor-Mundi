// com/weaverstudios/main/LanguageManager.java
package com.weaverstudios.main;

import java.util.Locale;
import java.util.ResourceBundle;

/*
 * LanguageManager handles the application's localization by managing language settings
 * and loading appropriate resource bundles for translations
 */
public class LanguageManager {
    // Stores the system's default language
    private static final String systemLang = Locale.getDefault().getLanguage();
    // ResourceBundle to hold the loaded language properties
    private static ResourceBundle resourceBundle;
    // Stores the currently selected language as a string
    private static String languageString;
    // Stores the current Locale object
    private static Locale language;


    // ========================================================================================
    //   Initializes the LanguageManager by detecting the system language and setting it up
    // ========================================================================================
    public static void initialize() {
        // Detect system language
        languageString = Locale.getDefault().getLanguage();
        
        // Create a Locale object from the detected language
        language = Locale.forLanguageTag(languageString);

        // Load the appropriate language properties file
        setLanguage(Locale.of(languageString));
    }


    // ====================================================================================
    //   Sets the application's language
    //   This method loads the appropriate properties file based on the provided locale
    // ====================================================================================
    public static void setLanguage(Locale locale) {
        language = locale;
        try {
            // Attempt to load the resource bundle for the specified language
            resourceBundle = ResourceBundle.getBundle("com.weaverstudios.languages.messages", locale);
        } catch (Exception e) {
            e.printStackTrace();
            // If an error occurs, fallback to English as the default language
            resourceBundle = ResourceBundle.getBundle("com.weaverstudios.languages.messages", Locale.ENGLISH);
        }
    }


    // =================================================
    //   Retrieves the localized text for a given key
    // =================================================
    public static String getText(String key) {
        return resourceBundle.getString(key);
    }


    // =====================================================================
    //    Finds the key associated with a given text in a specific locale
    //    This is useful for updating translations dynamically
    // =====================================================================
    public static String getKey(String text, Locale locale) {
        // Load the properties file for the given locale
        ResourceBundle bundle = ResourceBundle.getBundle("com.weaverstudios.languages.messages", locale);

        // Iterate over the keys in the resource bundle to find a matching text value
        for (String key : bundle.keySet()) {
            if (bundle.getString(key).equals(text)) {
                return key;
            }
        }
        return null;
    }

    // ===============================================================
    //    Returns the current locale being used by the application
    // ===============================================================
    public static Locale getCurrentLocale() {
        return language;
    }

    // =======================================================
    //   Returns the system's default language as a string
    // =======================================================
    public static String getSystemLang() {
        return systemLang;
    }
}
