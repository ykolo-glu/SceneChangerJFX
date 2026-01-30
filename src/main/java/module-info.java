module SceneChangerJFX {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;

    exports com.github.ykologlu.scenechangerjfx;
    opens com.github.ykologlu.scenechangerjfx to javafx.fxml;
}