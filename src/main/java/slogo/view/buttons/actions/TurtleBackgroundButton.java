package slogo.view.buttons.actions;

import javafx.scene.paint.Color;
import slogo.observer.BackgroundObservable;

/**
 * TurtleBackgroundButton is a ColorPicker that updates a BackgroundObservable with the selected
 * color, inheriting from AbstractColorButton.
 */
public class TurtleBackgroundButton extends AbstractColorButton {

  private final BackgroundObservable colorObservable;

  /**
   * Constructs a new TurtleBackgroundButton with an initial color set.
   *
   * @param colorObservable the BackgroundObservable to update
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
}
