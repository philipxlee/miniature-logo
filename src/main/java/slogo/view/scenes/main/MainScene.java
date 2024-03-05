package slogo.view.scenes.main;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import slogo.controller.CommandController;
import slogo.controller.ThemeController;
import slogo.observer.BackgroundObservable;
import slogo.observer.PenColorObservable;
import slogo.view.buttons.ControlButtonsBox;
import slogo.view.scenes.Scene;
import slogo.view.tabs.SideTabPane;

/**
 * MainScene represents the Main SLogo Scene.
 */
public class MainScene implements Scene {

  private final TurtlePane turtlePane;
  private final InputPane inputPane;
  private final SideTabPane sideTabPane;
  private final ControlButtonsBox controlButtonsBox;
  private javafx.scene.Scene scene;
  private BorderPane root;

  /**
   * Constructor for Display.
   *
   * @param width             width
   * @param height            height
   * @param commandController commandController
   */
  public MainScene(int width, int height, CommandController commandController) {
    // initialize observables
    BackgroundObservable colorObservable = new BackgroundObservable("#e0e0e0");
    PenColorObservable penColorObservable = new PenColorObservable("#000000");

    // initialize panes
    this.turtlePane = new TurtlePane(width, height);
    this.inputPane = new InputPane(height, commandController);
    this.sideTabPane = new SideTabPane();
    this.controlButtonsBox = new ControlButtonsBox(colorObservable, penColorObservable);

    // subscribe panes to models
    commandController.observeTurtle(turtlePane);
    commandController.observeLines(turtlePane);
    commandController.observeHistory(sideTabPane);
    turtlePane.setBackgroundColorObservable(colorObservable);
    turtlePane.setPenColorObservable(penColorObservable);

    initializeScene(width, height);
  }

  /**
   * Second initializer that accepts commands and updates the input pane.
   *
   * @param width             width
   * @param height            height
   * @param commandController commandController
   * @param commands          commands to display and run
   */
  public MainScene(int width, int height, CommandController commandController, String commands) {
    this(width, height, commandController);
    inputPane.setInputText(commands);
//    String rightCommand = "fd " + width/ 2;
//    inputPane.executeCommand(rightCommand, commandController);

    // Split the commands by newline and execute each command
    String[] commandLines = commands.split("\n");
    for (String command : commandLines) {
      inputPane.executeCommand(command, commandController);
    }
  }

  /**
   * Initialize the scene.
   *
   * @param width  width
   * @param height height
   */
  @Override
  public void initializeScene(int width, int height) {
    root = new BorderPane();
    root.setId("Main Scene");
    BorderPane topPane = new BorderPane();
    topPane.setTop(turtlePane.getDisplayPane());
    topPane.setBottom(controlButtonsBox);
    root.setTop(topPane);
    BorderPane bottomPane = new BorderPane();
    bottomPane.setLeft(inputPane.getInputBox());
    bottomPane.setRight(sideTabPane);
    root.setBottom(bottomPane);
    this.scene = new javafx.scene.Scene(root, width, height);
    System.out.println(ThemeController.getCurrentTheme());
    ThemeController.applyTheme(this.scene, ThemeController.getCurrentTheme());
    System.out.println(scene.getStylesheets());
  }

  /**
   * Get the scene.
   *
   * @return scene
   */
  @Override
  public javafx.scene.Scene getScene() {
    return this.scene;
  }

  /**
   * Get the root node.
   *
   * @return root
   */
  @Override
  public Node getRoot() {
    return root;
  }
}
