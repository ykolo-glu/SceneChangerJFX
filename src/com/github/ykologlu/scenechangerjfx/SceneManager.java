package com.github.ykologlu.scenechangerjfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class SceneManager {
    private HashMap<String, Scene> scenes;
    private String lastUsedFilepath;
    private Class<?> resourceClass;

    /**
     * the constructor makes a HashMap instance for scenes
     * and sets the last used filepath to an empty value
     */
    public SceneManager(){
        setLastUsedFilepath("");
        setScenes(new HashMap<>());
        this.resourceClass = null;
    }

    /**
     * Set the class to use for loading resources.
     * This should be a class from the application using the library.
     */
    public void setResourceClass(Class<?> resourceClass) {
        this.resourceClass = resourceClass;
    }

    /**
     * <p>
     *     loadScene is used to create a Scene using a fxml document of the users choosing.
     *     the fxml document has to be inside the resources file in a JavaFX project.
     * </p>
     * <p>
     *     if the user wants to use a cached Scene, the Scene that bears the same filepath as
     *     the one the user wants will be returned,
     *     unless there is no Scene that is stored in the cache with the same filepath, in which case a new Scene
     *     will be loaded with the desired fxml document.
     * </p>
     * <p>
     *     if the user does not want to use a cached Scene, all Scenes that use the same filepath will be deleted from cache
     *     and a new Scene will be loaded with the desired fxml document
     * </p>
     * <p>
     *     if the user chooses not to save the old Scene in cache, the old Scene will be removed from cache
     * </p>
     *
     * @param filepath
     * defines which fxml document should be loaded
     * @param cacheLastUsedScene
     * defines if the previous Scene should be cached or not
     * @param useCachedScene
     * defines if the current Scene should be fetched from the cache or not
     * @return
     * returns the wanted Scene
     * @throws IOException
     * throws an error if the wanted fxml document cannot be found
     */
    public Scene loadScene(String filepath, boolean cacheLastUsedScene, boolean useCachedScene) throws IOException {

        Scene scene = null;

        boolean cachedSceneNotFound = false;
        if(useCachedScene){
            scene = getSceneFromFilepath(filepath);
            if(scene == null){
                cachedSceneNotFound = true;
            }
        }
        if(!useCachedScene || cachedSceneNotFound){
            getScenes().remove(filepath);

            URL fxml = null;

//            if(resourceClass != null){
//
//                System.out.println("RESOLVING: /"
//                        + resourceClass.getPackage().getName().replace(".", "/")
//                        + "/" + filepath + ".fxml");
//
//
//                String filePathWithClassPath = resourceClass.getPackage().getName().replace(".", "/") + "/" + filepath;
//                fxml = fxmlHelper(filePathWithClassPath);
//                System.out.println(fxml);
//            }

            if (fxml == null) {
                fxml = fxmlHelper(filepath);
                System.out.println("DEBUG - : " + fxml);
            }

            if (fxml == null) {
                fxml = Thread.currentThread()
                        .getContextClassLoader()
                        .getResource(filepath + ".fxml");
            }

            if (fxml == null) {
                fxml = SceneManager.class.getResource("/" + filepath + ".fxml");
            }

            if (fxml == null) {
                throw new IllegalArgumentException(
                        "FXML not found: " + filepath + ".fxml"
                );
            }

            FXMLLoader loader = new FXMLLoader(fxml);
            scene = new Scene(loader.load());
        }

        getScenes().put(filepath, scene);
        if(getLastUsedFilepath() != null && !getLastUsedFilepath().isEmpty() && !cacheLastUsedScene){
            getScenes().remove(getLastUsedFilepath());
        }

        setLastUsedFilepath(filepath);

        return scene;
    }

    public URL fxmlHelper(String filepath){
        URL result = resourceClass.getResource("/" + filepath + ".fxml");
        System.out.println("Searching for: /" + filepath + ".fxml");
        System.out.println("Result: " + result);
        System.out.println("ResourceClass: " + resourceClass.getName());
        System.out.println("ResourceClass package: " + resourceClass.getPackage().getName());
        return result;
    }

    /**
     * @param filepath
     * filepath of the fxml of the requested Scene
     * @return
     * returns the requested Scene from the list. returns null if not fouund
     */
    public Scene getSceneFromFilepath(String filepath){
        return getScenes().get(filepath);
    }

    /**
     * @param scenes
     * Hashmap that replaces the current scenes Hashmap
     */
    public void setScenes(HashMap<String, Scene> scenes) {
        this.scenes = scenes;
    }

    /**
     * @return returns the current scenes Hashmap
     */
    public HashMap<String, Scene> getScenes() {
        return this.scenes;
    }

    /**
     * @return returns the last used filepath
     */
    public String getLastUsedFilepath() {
        return lastUsedFilepath;
    }

    /**
     * @param lastUsedFilepath
     * String that replaces the last used filepath
     */
    public void setLastUsedFilepath(String lastUsedFilepath) {
        this.lastUsedFilepath = lastUsedFilepath;
    }
}
