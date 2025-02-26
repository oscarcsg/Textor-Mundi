module com.weaverstudios.main {
    requires java.prefs;
    
    requires javafx.controls;
    requires transitive javafx.graphics;

    exports com.weaverstudios.main; // Allows other modules to use its public clases
}