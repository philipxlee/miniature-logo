package slogo.controller;


import javafx.application.Application;
import javafx.stage.Stage;
import slogo.model.api.CommandHistoryModel;
import slogo.model.api.TurtleModel;

/**
 * MainController initializes model, controllers and views
 */
public class MainController extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    // initialize models
    TurtleModel turtleModel = new TurtleModel();
    CommandHistoryModel commandHistoryModel = new CommandHistoryModel();

    // initialize controllers
    CommandController commandController = new CommandController(turtleModel, commandHistoryModel);

    // initialize views (through ViewController)
    ViewController viewController = new ViewController(stage, commandController);

    // configure stage
    stage.setTitle("SLogo");
    stage.setResizable(false);
    stage.show();
  }
}
