package slogo.view.scenes;

import javafx.scene.layout.BorderPane;
import slogo.controller.CommandController;
import slogo.view.Scene;
import slogo.view.tabs.SideTabPane;

public class MainScene implements Scene {

  private javafx.scene.Scene scene;
  private TurtleDisplay turtleDisplay;
  private CommandConsole commandInputBox;
  private SideTabPane sideTabPane;

  /**
   * Constructor for Display
   *
   * @param width             width
   * @param height            height
   * @param commandController commandController
   */
  public MainScene(int width, int height, CommandController commandController) {
    this.turtleDisplay = new TurtleDisplay(width, height);
    this.commandInputBox = new CommandConsole(width, height, commandController);
    this.sideTabPane = new SideTabPane();
    TurtleView turtleView = new TurtleView(turtleDisplay.getTurtleGraphic());
    commandController.observeTurtle(turtleView);
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
    BorderPane bottomPane = new BorderPane();
    bottomPane.setLeft(commandInputBox.getInputBox());
    bottomPane.setRight(sideTabPane);
    root.setBottom(bottomPane);
    this.scene = new javafx.scene.Scene(root, width, height);
  }

  /**
   * Get the scene
   *
   * @return scene
   */
  @Override
  public javafx.scene.Scene getScene() {
    return this.scene;
  }
}
