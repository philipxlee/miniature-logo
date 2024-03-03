package slogo.view.buttons.actions;

import javafx.scene.paint.Color;
import slogo.observer.PenColorObservable;

/**
 * PenColorButton is a ColorPicker that updates a PenColorObservable with the selected color,
 * inheriting from AbstractColorButton.
 */
public class PenColorButton extends AbstractColorButton {

  private final PenColorObservable colorObservable;

  /**
   * Constructs a new PenColorButton with an initial color set.
   *
   * @param colorObservable the PenColorObservable to update
   */
  public PenColorButton(PenColorObservable colorObservable) {
    super(Color.BLACK);
    this.colorObservable = colorObservable;
    setOnAction(event -> updatePenColorObservable());
  }

  private void updatePenColorObservable() {
    Color selectedColor = getValue();
    colorObservable.setColor(colorToString(selectedColor));
  }
}
