package slogo.view.buttons.actions;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import slogo.observer.BackgroundObservable;

/**
 * TurtleBackgroundButton is a ColorPicker that updates a BackgroundObservable with the selected
 * color.
 */
public class TurtleBackgroundButton extends ColorPicker {

  private final BackgroundObservable colorObservable;

  /**
   * Constructs a new TurtleBackgroundButton.
   *
   * @param colorObservable the observable to update with the selected color
   */
  public TurtleBackgroundButton(BackgroundObservable colorObservable) {
    super(Color.BLACK);
    this.colorObservable = colorObservable;
    setOnAction(event -> updateColorObservable());
  }

  private void updateColorObservable() {
    Color selectedColor = getValue();
    colorObservable.setColor(colorToString(selectedColor));
  }

  private String colorToString(Color color) {
    return String.format("#%02X%02X%02X",
        (int) (color.getRed() * 255),
        (int) (color.getGreen() * 255),
        (int) (color.getBlue() * 255));
  }
}
