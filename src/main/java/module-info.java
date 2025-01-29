module com.weaverstudios.main {
    requires javafx.controls;
    requires transitive javafx.graphics;

    exports com.weaverstudios.main; // Permite que otros modulos usen sus clases públicas
}