package slogo.controller;


import javafx.application.Application;
import javafx.stage.Stage;
import slogo.model.api.CommandHistoryModel;
import slogo.model.api.LineModel;
import slogo.model.api.TurtleModel;

/**
 * MainController initializes modules for application
 */
public class MainController extends Application {

  /**
   * Connects Model, Views and Controllers together
   *
   * @param stage primary stage of the application
   */
  @Override
  public void start(Stage stage) throws Exception {
    // initialize models
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    CommandHistoryModel commandHistoryModel = new CommandHistoryModel();

    // initialize controllers
    CommandController commandController = new CommandController(turtleModel, commandHistoryModel,
        lineModel);
    ViewController viewController = new ViewController(stage, commandController);

    // initialize views (through ViewController)
    viewController.initializeViews();
  }
}
