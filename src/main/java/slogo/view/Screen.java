package slogo.view;

import javafx.stage.Stage;

public abstract class Screen implements IScreen {
  // Declare a stage field
  protected Stage stage;

  // Create a constructor with a stage parameter
  public Screen(Stage stage) {
    this.stage = stage;
  }

  // Implement the show method
  @Override
  public void show() {
    stage.show();
  }

  // Implement the hide method
  @Override
  public void hide() {
    stage.hide();
  }
}
