import javafx.stage.Stage;

import java.io.IOException;

public class StageManager {

    private Stage stage;
    private SceneManager sceneManager;

    public StageManager(){

        setStage(new Stage());
        setSceneManager(new SceneManager());
    }

    public void changeScene(String filepath, boolean cacheLastUsedScene, boolean useCachedScene) throws IOException {
        stage.setScene(sceneManager.loadScene(filepath, cacheLastUsedScene, useCachedScene));
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
