package com.github.ykologlu.scenechangerjfx;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DebugController implements Initializable {

    @javafx.fxml.FXML
    private ListView<String> sceneListView;
    @javafx.fxml.FXML
    private ListView<String> stageManagerListView;

    private HashMap<String, StageManager> stageManagers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       setStageManagers(StageProvider.getStageManagerInstances());
       stageManagerListView.getItems().addAll(getStageManagers().keySet());

    }

    public void LoadScenesOfStage(){

        sceneListView.getItems().clear();
        SceneManager sceneManager = StageProvider.getStageFromName(
                                    stageManagerListView
                                    .getSelectionModel()
                                    .getSelectedItem())
                                    .getSceneManager();

        sceneListView.getItems().addAll(sceneManager.getScenes().keySet());

    }

    public HashMap<String, StageManager> getStageManagers() {
        return stageManagers;
    }

    public void setStageManagers(HashMap<String, StageManager> stageManagers) {
        this.stageManagers = stageManagers;
    }

}
