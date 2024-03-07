package slogo.view.scenes.start;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.TimeUnit;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import slogo.controller.command.CommandController;
import slogo.controller.display.ViewController;
import slogo.model.api.data.CommandHistoryModel;
import slogo.model.api.data.LineModel;
import slogo.model.api.data.TurtleModel;
import slogo.model.api.data.VariablesModel;
import util.DukeApplicationTest;

public class AlertTest extends DukeApplicationTest {

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
    VariablesModel variablesModel = new VariablesModel();

    // initialize controllers
    CommandController commandController = new CommandController(turtleModel, lineModel,
        commandHistoryModel, variablesModel);
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

    // Find the input field by its ID set in the InputPane
    TextArea myInput = lookup("#input-pane-text-area").query();
    assertNotNull(myInput);

    writeInputTo(myInput, "$ afbsaoadfbka");
    clickOn(myInput);
    push(KeyCode.BACK_SPACE);
    press(KeyCode.ENTER);
    push(KeyCode.ENTER);
    sleep(5, TimeUnit.SECONDS);


    // Assuming nextPage is the content of the next scene
    assertNotNull(lookup("error"));
  }
}

