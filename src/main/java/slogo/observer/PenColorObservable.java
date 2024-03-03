package slogo.observer;

/**
 * PenColorObservable is an observable that notifies its observers when its color changes.
 */
public class PenColorObservable extends AbstractObservable {

  private String color;

  /**
   * Constructs a new PenColorObservable.
   *
   * @param initialColor the initial color of the observable
   */
  public PenColorObservable(String initialColor) {
    super();
    this.color = initialColor;
  }

  /**
   * Returns the color of the observable.
   *
   * @return the color of the observable
   */
  public String getColor() {
    return color;
  }

  /**
   * Sets the color of the observable.
   *
   * @param color the new color of the observable
   */
  public void setColor(String color) {
    this.color = color;
    notifyObservers();
  }
}
