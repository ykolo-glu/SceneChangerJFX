import java.util.ArrayList;
import java.util.HashMap;

public class StageProvider {

    public static HashMap<String, StageManager> stageManagerInstances = new HashMap<>();

    public static StageManager getStageManager(String stageName){

        StageManager stageManager = getStageFromName(stageName);
        if(stageManager != null){
            return stageManager;
        }

        stageManager = new StageManager();
        stageManagerInstances.put(stageName, stageManager);
        return stageManager;
    }

    public static void removeStageManager(String stageName){

        StageManager stageManager = getStageFromName(stageName);
        if(stageManager != null){
            stageManager.getStage().close();
        }
        stageManagerInstances.remove(stageName);

    }

    public static StageManager resetStage(String stageName){

        removeStageManager(stageName);

        return getStageManager(stageName);
    }

    public static StageManager getStageFromName(String stageName){
        return stageManagerInstances.get(stageName);
    }


}
