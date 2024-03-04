package slogo.view.buttons.actions;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

/**
 * AbstractColorButton provides the common functionality for buttons that use a ColorPicker to
 * select and convert a color to a string.
 */
public abstract class AbstractColorButton extends ColorPicker {

  /**
   * Constructs a new AbstractColorButton with an initial color set.
   *
   * @param initialColor the initial color of the ColorPicker
   */
  public AbstractColorButton(Color initialColor) {
    super(initialColor);
  }

  /**
   * Converts a Color object to a hex string.
   *
   * @param color the Color to convert
   * @return the hex string representation of the color
   */
  public String colorToString(Color color) {
    return String.format("#%02X%02X%02X",
        (int) (color.getRed() * 255),
        (int) (color.getGreen() * 255),
        (int) (color.getBlue() * 255));
  }
}
