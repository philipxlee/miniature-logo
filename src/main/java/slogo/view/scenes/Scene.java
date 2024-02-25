package slogo.view.scenes;

import javafx.scene.Node;

/**
 * Scene abstraction represents a scene being displayed.
 */
public interface Scene {

  /**
   * Initialize the scene.
   *
   * @param width  width of scene
   * @param height height of scenes
   */
  void initializeScene(int width, int height);

  /**
   * Get the scene object.
   *
   * @return Java FX Scene object that represents the scene
   */
  javafx.scene.Scene getScene();

  /**
   * Get the root of the scene.
   *
   * @return Root of the scene
   */
  Node getRoot();
}
