package slogo.controller.display;

import javafx.stage.Stage;
import slogo.controller.command.CommandController;
import slogo.controller.config.ThemeController;
import slogo.view.scenes.Scene;
import slogo.view.scenes.start.StartScene;

/**
 * ViewController manages Scenes and switches between them.
 */
public class ViewController implements SceneSwitcher {

  private static final int WIDTH = 1300;
  private static final int HEIGHT = 800;
  private final Stage stage;
  private final CommandController commandController;

  /**
   * ViewController constructor. Initialized with a JavaFX stage and a CommandController
   *
   * @param stage             primary stage of JavaFX application
   * @param commandController CommandController used by the view
   */
  public ViewController(Stage stage, CommandController commandController) {
    this.stage = stage;
    this.commandController = commandController;
    stage.setTitle("SLogo");
    stage.setResizable(false);
    stage.show();
  }

  /**
   * Initialize Start Scene.
   */
  public void initializeViews() {
    switchToScene(new StartScene(WIDTH, HEIGHT, this, commandController));
  }

  /**
   * Switch to new Scene.
   *
   * @param scene is new Scene to switch to
   */
  @Override
  public void switchToScene(Scene scene) {
    scene.initializeScene(WIDTH, HEIGHT);
    ThemeController.applyTheme(scene.getScene(), ThemeController.getCurrentTheme());
    stage.setScene(scene.getScene());
  }
}
