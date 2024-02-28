package slogo.view.buttons.actions;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import slogo.observer.BackgroundObservable;

public class TurtleBackgroundButton extends ColorPicker {

  private final BackgroundObservable colorObservable;

  public TurtleBackgroundButton(BackgroundObservable colorObservable) {
    super(Color.BLACK);
    this.colorObservable = colorObservable;
    setOnAction(event -> updateColorObservable());
  }

  private void updateColorObservable() {
    Color selectedColor = getValue();
    colorObservable.setColor(toRgbString(selectedColor));
  }

  private String toRgbString(Color color) {
    return String.format("#%02X%02X%02X",
        (int) (color.getRed() * 255),
        (int) (color.getGreen() * 255),
        (int) (color.getBlue() * 255));
  }
}
