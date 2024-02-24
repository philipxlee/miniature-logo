package slogo.controller;

import javafx.stage.Stage;
import slogo.view.scenes.Scene;
import slogo.view.scenes.start.StartScene;

/**
 * ViewController manages Scenes and switches between them
 */
public class ViewController implements SceneSwitcher {

  private static final int WIDTH = 1000;
  private static final int HEIGHT = 600;
  private final Stage stage;

  /**
   * ViewController constructor. Initialized with a JavaFX stage and a CommandController
   *
   * @param stage:             primary stage of JavaFX application
   * @param commandController: CommandController used by the view
   */
  public ViewController(Stage stage, CommandController commandController) {
    this.stage = stage;
    switchToScene(new StartScene(WIDTH, HEIGHT, this, commandController));
  }

  /**
   * Switch to new Scene
   *
   * @param scene: new Scene to switch to
   */
  @Override
  public void switchToScene(Scene scene) {
    scene.initializeScene(WIDTH, HEIGHT);
    stage.setScene(scene.getScene());
  }
}
