package slogo.view.tabs;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import slogo.view.scenes.main.TurtlePane;

/**
 * PenPropertiesTab represents the view of the pen properties tab.
 */
public class PenPropertiesTab implements TabContent {

  private final VBox content = new VBox();
  private final TurtlePane turtlePane;

  /**
   * Constructs a new PenPropertiesTab.
   *
   * @param turtlePane the turtle pane to control
   */
  public PenPropertiesTab(TurtlePane turtlePane) {
    this.turtlePane = turtlePane;
    initializeUi();
  }

  /**
   * Returns the content of the tab.
   *
   * @return the content of the tab
   */
  @Override
  public Node getContent() {
    return content;
  }

  private void initializeUi() {
    ToggleButton penToggle = new ToggleButton("Pen Up/Down");
    ColorPicker colorPicker = new ColorPicker();
    Slider thicknessSlider = new Slider(1, 10, 1);
    colorPicker.setOnAction(e -> turtlePane.setPenColor(colorPicker.getValue()));
    penToggle.setOnAction(e -> turtlePane.setPenUp(penToggle.isSelected()));
    thicknessSlider.valueProperty().addListener((obs, oldVal, newVal) ->
        turtlePane.setPenThickness(newVal.doubleValue()));
    content.getChildren().addAll(penToggle, colorPicker, thicknessSlider);
  }

}
