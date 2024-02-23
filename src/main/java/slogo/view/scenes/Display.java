package slogo.view.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import slogo.controller.CommandController;
import slogo.view.GeneralScene;

public class Display implements GeneralScene {

  private Scene scene;
  private TurtleDisplay turtleDisplay;
  private CommandConsole commandInputBox;

  /**
   * Constructor for Display
   *
   * @param width width
   * @param height height
   * @param commandController commandController
   */
  public Display(int width, int height, CommandController commandController) {
    this.turtleDisplay = new TurtleDisplay(width, height);
    this.commandInputBox = new CommandConsole(width, height, commandController);
    TurtleView turtleView = new TurtleView(turtleDisplay.getTurtleGraphic());
    commandController.getTurtleModel().addObserver(turtleView); // NOTE: Observer moved here...
    initializeScene(width, height);
  }

  /**
   * Initialize the scene
   *
   * @param width  width
   * @param height height
   */
  @Override
  public void initializeScene(int width, int height) {
    BorderPane root = new BorderPane();
    root.setTop(turtleDisplay.getDisplayPane());
    root.setBottom(commandInputBox.getInputBox());
    this.scene = new Scene(root, width, height);
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
