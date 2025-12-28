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
```java
StageManager mainStageManager = StageProvider.getStageManager("mainStage");
mainStageManager.setStage(stage);
mainStageManager.getSceneManager().setResourceClass(HelloApplication.class);
mainStageManager.changeScene("hello-view", false, false);
```

## Module Setup
it is neccesary to open your packages to this library. not only one main package, but the packages that contain the fxml files.
```java
opens your.package to SceneChangerJFX;
```
