import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class SceneManager {
    private HashMap<String, Scene> scenes;
    private String lastUsedFilepath;

    public SceneManager(){
        setLastUsedFilepath("");
        setScenes(new HashMap<String, Scene>());
    }

    public Scene loadScene(String filePath, boolean cacheLastUsedScene, boolean useCachedScene) throws IOException {

        Scene scene = null;

        boolean cachedSceneNotFound = false;
        if(useCachedScene){
            scene = getSceneFromFilepath(filePath);
            if(scene == null){
                cachedSceneNotFound = true;
            }
        }
        if(!useCachedScene || cachedSceneNotFound){
            getScenes().remove(filePath);

            URL fxml = Thread.currentThread()
                    .getContextClassLoader()
                    .getResource(filePath + ".fxml");

            if (fxml == null) {
                throw new IllegalArgumentException(
                        "FXML not found: " + filePath + ".fxml"
                );
            }

            FXMLLoader loader = new FXMLLoader(fxml);

            scene = new Scene(loader.load());
        }

        getScenes().put(filePath, scene);
        if(!getLastUsedFilepath().isEmpty() && getLastUsedFilepath() != null && !cacheLastUsedScene){
            getScenes().remove(getLastUsedFilepath());
        }

        return scene;
    }

    public void setScenes(HashMap<String, Scene> scenes) {
        this.scenes = scenes;
    }
    public HashMap<String, Scene> getScenes() {
        return this.scenes;
    }

    public Scene getSceneFromFilepath(String filepath){
        return getScenes().get(filepath);
    }

    public String getLastUsedFilepath() {
        return lastUsedFilepath;
    }

    public void setLastUsedFilepath(String lastUsedFilepath) {
        this.lastUsedFilepath = lastUsedFilepath;
    }
}
