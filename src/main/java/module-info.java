module com.weaverstudios.main {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires java.prefs;

    exports com.weaverstudios.main; // Allows other modules to use its public clases
}