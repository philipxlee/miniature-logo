package slogo.view.scenes.start;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.TimeUnit;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import slogo.controller.CommandController;
import slogo.controller.ViewController;
import slogo.model.api.data.CommandHistoryModel;
import slogo.model.api.data.LineModel;
import slogo.model.api.data.TurtleModel;
import util.DukeApplicationTest;

public class StartSceneTest extends DukeApplicationTest {

  private Button startButton;

  /**
   * Start test version of application (note this is basically just a copy of the Main class's
   * start() method).
   */
  @Override
  public void start(Stage stage) {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    CommandHistoryModel commandHistoryModel = new CommandHistoryModel();

    // initialize controllers
    CommandController commandController = new CommandController(turtleModel, lineModel,
        commandHistoryModel);
    ViewController viewController = new ViewController(stage, commandController);
    // initialize views (through ViewController)
    viewController.initializeViews();
    startButton = lookup("Start Game").query();

  }

  @Test
  void testStartGameButton() {
    // Simulate clicking the start game button
    clickOn(startButton);
    // PAUSE: give game time to load
    sleep(2, TimeUnit.SECONDS);

    // Assuming nextPage is the content of the next scene
    assertNotNull(lookup("Main Scene"));

  }

}
