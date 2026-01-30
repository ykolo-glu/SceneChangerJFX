package main.java.com.github.ykologlu.scenechangerjfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DebugStageManager extends StageManager{

    public DebugStageManager() {
        super();
    }

    @Override
    public void changeScene(String filepath, boolean cacheLastUsedScene, boolean useCachedScene) throws IOException {

    }

    @Override
    public void setStage(Stage stage) {
        super.setStage(stage);
        getStage().setTitle("Debug Stage");
        try {
            getStage().setScene(loadInternalFxml());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Scene loadInternalFxml() throws IOException {
        URL fxmlUrl = DebugStageManager.class.getResource("debug.fxml");

        if (fxmlUrl == null) {
            throw new IOException("debug.fxml not found");
        }

        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        return new Scene(loader.load());
    }


    }
