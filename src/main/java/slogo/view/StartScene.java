package slogo.view;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import slogo.view.SceneSwitcher;

public class StartScene implements GeneralScene {

  private Scene scene;
  private SceneSwitcher switcher;

  public StartScene(int width, int height, SceneSwitcher switcher) {
    this.switcher = switcher;
    initializeScene(width, height);
  }

  @Override
  public void initializeScene(int width, int height) {
    VBox parentBox = new VBox(10);
    parentBox.setAlignment(Pos.CENTER);

    // Initialize title text and add click handler
    Text title = new Text("SLogo");
    title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
    Text instruction = new Text("Click to start");
    instruction.setStyle("-fx-font-size: 16px;");
    parentBox.setOnMouseClicked(event -> switcher.switchToScene(new CommandLineScene(width, height)));
    parentBox.getChildren().addAll(title);
    this.scene = new javafx.scene.Scene(parentBox, width, height);
  }

  @Override
  public Scene getScene() {
    return this.scene;
  }
}
