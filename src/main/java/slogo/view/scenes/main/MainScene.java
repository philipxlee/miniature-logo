package slogo.view.scenes.main;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import slogo.controller.command.CommandController;
import slogo.controller.config.ThemeController;
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
   * MainScene Constructor. Initializes the main scene with the given width and height.
   *
   * @param width             width
   * @param height            height
   * @param commandController commandController
   */
  public MainScene(int width, int height, CommandController commandController) {
    this(width, height, commandController, null);
  }

  /**
   * MainScene Constructor. Initializes the main scene with the given width and height and executes
   * the given commands.
   *
   * @param width             width
   * @param height            height
   * @param commandController commandController
   * @param commands          commands
   */
  public MainScene(int width, int height, CommandController commandController, String commands) {
    BackgroundObservable colorObservable = new BackgroundObservable("#e0e0e0");
    PenColorObservable penColorObservable = new PenColorObservable("#000000");

    this.turtlePane = new TurtlePane(width, height);
    this.inputPane = new InputPane(height, commandController);
    this.sideTabPane = new SideTabPane(commandController, turtlePane);
    this.controlButtonsBox = new ControlButtonsBox(colorObservable, penColorObservable, turtlePane);

    commandController.observeTurtle(turtlePane);
    commandController.observeLines(turtlePane);
    commandController.observeHistory(sideTabPane);
    commandController.observeVariables(sideTabPane);
    turtlePane.setBackgroundColorObservable(colorObservable);
    turtlePane.setPenColorObservable(penColorObservable);

    // Execute the commands if provided.
    if (commands != null) {
      Platform.runLater(() -> { // Important: Ensures JavaFX panes are correctly set first
        String[] commandLines = commands.split("\n");
        inputPane.setInputText(commands);
        for (String command : commandLines) {
          inputPane.executeCommand(command, commandController);
        }
      });
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
    ThemeController.applyTheme(this.scene, ThemeController.getCurrentTheme());
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
