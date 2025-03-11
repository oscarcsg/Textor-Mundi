module com.weaverstudios.main {
    requires java.prefs;
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires java.sql; // Para SQLite
    
    exports com.weaverstudios.main;
    opens com.weaverstudios.main to javafx.graphics;
}
