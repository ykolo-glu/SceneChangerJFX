# SceneChangerJFX

A JavaFX Library for easy Scene-Management with automatic package-resolution.

## Features
- scene loading and caching
- Automatic package path resolution
- Scene caching to improve performance

## Installation
follow instructions at:
[JitPack](https://jitpack.io/#ykolo-glu/SceneChangerJFX/)

## Usage example
in your Application class:
```java
StageManager mainStageManager = StageProvider.getStageManager("mainStage");
mainStageManager.setStage(stage);
mainStageManager.getSceneManager().setResourceClass(HelloApplication.class);
mainStageManager.changeScene("hello-view", false, false);
```
the Stagemanager is used to manage wich stage is being used.
you set the stage that javafx creates per default as the stage for the mainStageManager.
you then give the library the resource class you use.
the first parameter defines the filepath. the second parameter defines if the current scene should be cached or not, the last parameter defines if the new scene should be loaded from cache or not

in your Controller class:
```java
StageManager mainStageManager = StageProvider.getStageManager("mainStage");
mainStageManager.changeScene("second-view", true, false);
```
the Stagemanager is used to manage wich stage is being used.
the first parameter defines the filepath. the second parameter defines if the current scene should be cached or not, the last parameter defines if the new scene should be loaded from cache or not

## Module Setup
it is neccesary to open your packages to this library. not only one main package, but the packages that contain the fxml files.
```java
opens your.package to SceneChangerJFX;
```
