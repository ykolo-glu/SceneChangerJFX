package main.java.com.github.ykologlu.scenechangerjfx;

import javafx.stage.Stage;

import java.io.IOException;

public class StageManager {

    private Stage stage;
    private SceneManager sceneManager;

    /**
     * the constructor makes a Stage instance for stage and makes a SceneManager instance for sceneManager
     */
    public StageManager(){

        setStage(new Stage());
        setSceneManager(new SceneManager());
    }

    /**
     * Is used to change the scenes inside the stage
     *
     * @param filepath
     * defines which fxml document should be loaded
     * @param cacheLastUsedScene
     * defines if the previous Scene should be cached or not
     * @param useCachedScene
     * defines if the current Scene should be fetched from the cache or not
     * @throws IOException
     * throws an error if the wanted fxml document cannot be found
     *
     */
    public void changeScene(String filepath, boolean cacheLastUsedScene, boolean useCachedScene) throws IOException {
        stage.setScene(sceneManager.loadScene(filepath, cacheLastUsedScene, useCachedScene));
    }

    /**
     * @return
     * returns the current stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @param stage
     * Stage that replaces the current Stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * @return
     * returns the current SceneManager
     */
    public SceneManager getSceneManager() {
        return sceneManager;
    }

    /**
     * @param sceneManager
     * SceneManager that replaces the current SceneManager
     */
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
