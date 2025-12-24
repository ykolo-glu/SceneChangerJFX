package SceneChanger;

import java.util.HashMap;

public class StageProvider {

    public static HashMap<String, StageManager> stageManagerInstances = new HashMap<>();

    /**
     * <p>
     *     contains all StageManagers and provides them to users as Singletons.
     * </p>
     *
     * @param stageName
     * defines what the SceneChanger.StageManager should be called, this name is used as a unique identifier
     * @return
     * returns the wanted SceneChanger.StageManager, if a SceneChanger.StageManager exists with the same name,
     * then the existing instance will be returned, else a new instance gets created
     */
    public static StageManager getStageManager(String stageName){

        StageManager stageManager = getStageFromName(stageName);
        if(stageManager != null){
            return stageManager;
        }

        stageManager = new StageManager();
        stageManagerInstances.put(stageName, stageManager);
        return stageManager;
    }

    /**
     * closes the stage within the requested SceneChanger.StageManager and then removes the Stagemanager
     *
     * @param stageName
     * unique identifier to distinguish between StageManagers
     */
    public static void removeStageManager(String stageName){

        StageManager stageManager = getStageFromName(stageName);
        if(stageManager != null){
            stageManager.getStage().close();
        }
        stageManagerInstances.remove(stageName);

    }

    /**
     * deletes the old SceneChanger.StageManager and then creates a new one instead
     *
     * @param stageName
     * unique identifier to distinguish between StageManagers
     * @return
     * returns the fresh SceneChanger.StageManager
     */
    public static StageManager resetStageManager(String stageName){

        removeStageManager(stageName);

        return getStageManager(stageName);
    }

    /**
     * @param stageName
     * unique identifier to distinguish between StageManagers
     * @return
     * returns the requested Stage from the list, returns null if not found
     */
    public static StageManager getStageFromName(String stageName){
        return stageManagerInstances.get(stageName);
    }


}
