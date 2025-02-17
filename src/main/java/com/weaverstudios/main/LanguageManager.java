// com/weaverstudios/main/LanguageManager.java
package com.weaverstudios.main;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
    private static final String systemLang = Locale.getDefault().getLanguage();
    private static ResourceBundle resourceBundle;
    private static String languageString;
    private static Locale language;

    // Método para inicializar el LanguageManager y cargar el idioma
    public static void initialize() {
        // Detectar el idioma del sistema
        languageString = Locale.getDefault().getLanguage();
        language = Locale.forLanguageTag(languageString);

        // Cargar el archivo de propiedades según el idioma
        setLanguage(Locale.of(languageString));
    }

    // Método para cambiar el idioma (puede ser llamado desde la interfaz de usuario)
    public static void setLanguage(Locale locale) {
        language = locale;
        try {
            // Intentar cargar el archivo de propiedades para el idioma seleccionado
            resourceBundle = ResourceBundle.getBundle("com.weaverstudios.languages.messages", locale);
        } catch (Exception e) {
            e.printStackTrace();
            // Si ocurre un error, cargar el archivo por defecto (inglés)
            resourceBundle = ResourceBundle.getBundle("com.weaverstudios.languages.messages", Locale.ENGLISH);
        }
    }

    // Método para obtener los textos (para ser usados en la interfaz de usuario)
    public static String getText(String key) {
        return resourceBundle.getString(key);
    }

    // Method to obtain the keys of texts (for update language)
    public static String getKey(String text, Locale locale) {
        // Charge properties file by locale
        ResourceBundle bundle = ResourceBundle.getBundle("com.weaverstudios.languages.messages", locale);

        // Iterates over keys from ResourceBundle and search equal value to text
        for (String key : bundle.keySet()) {
            if (bundle.getString(key).equals(text)) {
                return key;
            }
        }
        return null;
    }

    public static Locale getCurrentLocale() {
        return language;
    }

    public static String getSystemLang() {
        return systemLang;
    }
}
