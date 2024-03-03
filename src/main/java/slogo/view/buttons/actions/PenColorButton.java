package slogo.view.buttons.actions;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import slogo.observer.PenColorObservable;

public class PenColorButton extends ColorPicker {

  private final PenColorObservable colorObservable;

  /**
   * Constructs a new PenColorButton.
   */
  public PenColorButton(PenColorObservable colorObservable) {
    super(Color.BLACK); // This sets the initial color of the button.
    this.colorObservable = colorObservable;
    setOnAction(event -> updatePenColorObservable());
  }

  /**
   * Performs the action associated with the button.
   */
  public void updatePenColorObservable() {
    Color selectedColor = getValue();
    colorObservable.setColor(toRGBString(selectedColor));
  }

  private String toRGBString(Color color) {
    return String.format("#%02X%02X%02X",
        (int) (color.getRed() * 255),
        (int) (color.getGreen() * 255),
        (int) (color.getBlue() * 255));
  }

}
