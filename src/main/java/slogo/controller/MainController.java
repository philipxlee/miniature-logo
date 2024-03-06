package slogo.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import slogo.model.api.data.CommandHistoryModel;
import slogo.model.api.data.LineModel;
import slogo.model.api.data.TurtleModel;
import slogo.model.api.data.VariablesModel;

/**
 * MainController initializes modules for application.
 */
public class MainController extends Application {

  /**
   * Connects Model, Views and Controllers together.
   *
   * @param stage primary stage of the application
   */
  @Override
  public void start(Stage stage) throws Exception {
    // initialize models
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
  }
}
