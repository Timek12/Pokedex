module org.frontend.pokedex {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires com.fasterxml.jackson.databind;

    exports org.pokedex.pokedex.models to com.fasterxml.jackson.databind;
    opens org.pokedex.pokedex.models to com.fasterxml.jackson.databind, javafx.base;

    opens org.pokedex.pokedex to javafx.fxml;
    exports org.pokedex.pokedex;

    exports org.pokedex.pokedex.controllers to javafx.fxml;
    opens org.pokedex.pokedex.controllers to javafx.fxml;
}