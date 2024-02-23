package slogo.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import slogo.view.GeneralScene;
import slogo.view.StartScene;
import slogo.view.SceneSwitcher;

public class ViewController extends Application implements SceneSwitcher {

  private static final int WIDTH = 1000;
  private static final int HEIGHT = 600;
  private Stage stage;
  private CommandController commandController;

  /**
   * Start the view
   *
   * @param stage the primary stage for this application, onto which
   * the application scene can be set.
   * Applications may create other stages, if needed, but they will not be
   * primary stages.
   */
  @Override
  public void start(Stage stage) {
    this.stage = stage;
    this.commandController = new CommandController();
    switchToScene(new StartScene(WIDTH, HEIGHT, this, commandController));
    stage.setTitle("SLogo Application");
    stage.setResizable(false);
    stage.show();
  }

  /**
   * Switch to new GeneralScene
   *
   * @param scene: new GeneralScene to switch to
   */
  @Override
  public void switchToScene(GeneralScene scene) {
    scene.initializeScene(WIDTH, HEIGHT);
    stage.setScene(scene.getScene());
  }
}
