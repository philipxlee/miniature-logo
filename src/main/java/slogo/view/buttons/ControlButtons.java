package slogo.view.buttons;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlButtons extends HBox {

  private final Button button1;
  private final Button button2;
  // Add more buttons as needed

  public ControlButtons() {
    this.button1 = new Button("Button 1");
    this.button2 = new Button("Button 2");
    // Initialize more buttons as needed

    setupLayout();
  }

  private void setupLayout() {
    this.setSpacing(10);
    this.setPadding(new Insets(10));
    this.getChildren().addAll(button1, button2);
    // Add more buttons to the layout

    // Add button actions if needed
    button1.setOnAction(event -> {
      // Handle button1 action
    });

    button2.setOnAction(event -> {
      // Handle button2 action
    });
    // Add actions for more buttons
  }
}
