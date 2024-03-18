package slogo.view.tabs;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import slogo.view.scenes.main.TurtlePane;

/**
 * PenPropertiesTab is the tab that displays pen properties.
 */
public class PenPropertiesTab implements TabContent {

  private final VBox content = new VBox();
  private final TurtlePane turtlePane;

  /**
   * Constructor for PenPropertiesTab.
   *
   * @param turtlePane The pane to control pen properties
   */
  public PenPropertiesTab(TurtlePane turtlePane) {
    this.turtlePane = turtlePane;
    initializeUi();
  }

  /**
   * Return a node with the pen properties content.
   *
   * @return Node representing the pane.
   */
  @Override
  public Node getContent() {
    return content;
  }

  private void initializeUi() {
    content.getChildren().addAll(createPenToggle(), createColorPicker(), createThicknessSlider());
  }

  private ToggleButton createPenToggle() {
    ToggleButton penToggle = new ToggleButton("Pen Up/Down");
    penToggle.setOnAction(e -> turtlePane.setPenUp(penToggle.isSelected()));
    return penToggle;
  }

  private ColorPicker createColorPicker() {
    ColorPicker colorPicker = new ColorPicker();
    colorPicker.setOnAction(e -> turtlePane.setPenColor(colorPicker.getValue()));
    return colorPicker;
  }

  private Slider createThicknessSlider() {
    Slider thicknessSlider = new Slider(1, 10, 1);
    thicknessSlider.valueProperty().addListener((obs, oldVal, newVal) ->
        turtlePane.setPenThickness(newVal.doubleValue()));
    return thicknessSlider;
  }
}
