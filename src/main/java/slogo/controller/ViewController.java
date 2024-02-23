package slogo.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import slogo.view.GeneralScene;
import slogo.view.StartScene;
import slogo.view.SceneSwitcher;

public class ViewController extends Application implements SceneSwitcher {

  private static final int WIDTH = 1000;
  private static final int HEIGHT = 600;
  private Stage stage;
  private CommandController commandController;

  @Override
  public void start(Stage stage) {
    this.stage = stage;
    this.commandController = new CommandController();
    switchToScene(new StartScene(WIDTH, HEIGHT, this, commandController));
    stage.setTitle("SLogo Application");
    stage.setResizable(false);
    stage.show();
  }

  @Override
  public void switchToScene(GeneralScene scene) {
    scene.initializeScene(WIDTH, HEIGHT);
    stage.setScene(scene.getScene());
  }
}
