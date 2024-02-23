package slogo.view;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import slogo.controller.CommandController;
import slogo.view.scenes.Display;

public class StartScene implements GeneralScene {

  private Scene scene;
  private SceneSwitcher switcher;
  private CommandController commandController;

  /**
   * Constructor for StartScene
   *
   * @param width width
   * @param height height
   * @param switcher switcher
   * @param commandController commandController
   */
  public StartScene(int width, int height, SceneSwitcher switcher, CommandController commandController) {
    this.commandController = commandController;
    this.switcher = switcher;
    initializeScene(width, height);
  }


  /**
   * Initialize start screen with title and click handler
   *
   * @param width width
   * @param height height
   */
  @Override
  public void initializeScene(int width, int height) {
    VBox parentBox = new VBox(10);
    parentBox.setAlignment(Pos.CENTER);

    // Initialize title text and add click handler
    Text title = new Text("SLogo");
    title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
    Text instruction = new Text("Click to start");
    instruction.setStyle("-fx-font-size: 16px;");
    Display commandLineScene = new Display(width, height, commandController);
    parentBox.setOnMouseClicked(event -> switcher.switchToScene(commandLineScene));
    parentBox.getChildren().addAll(title);
    this.scene = new javafx.scene.Scene(parentBox, width, height);
  }

  /**
   * Get the scene
   *
   * @return scene
   */
  @Override
  public Scene getScene() {
    return this.scene;
  }
}
