package slogo.view.tabs;

import java.util.LinkedHashMap;
import java.util.Map;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import slogo.controller.command.CommandController;
import slogo.controller.config.LanguageController;
import slogo.model.api.data.CommandHistoryModel;
import slogo.model.api.data.VariablesModel;
import slogo.observer.Observable;
import slogo.observer.Observer;
import slogo.view.scenes.main.TurtlePane;

public class SideTabPane extends TabPane implements Observer {

  private final CommandController commandController;
  private final Map<String, TabContent> tabMap = new LinkedHashMap<>();
  private final TurtlePane turtlePane;

  public SideTabPane(CommandController commandController, TurtlePane turtlePane) {
    this.commandController = commandController;
    this.turtlePane = turtlePane;
    setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    initializeAndConstructTabs();
  }

  private void initializeAndConstructTabs() {
    addTab("CommandHistory", new CommandHistoryTab(commandController));
    addTab("HelpDocs", new HelpDocTab());
    addTab("UserVariables", new UserVariablesTab(commandController));
    addTab("UserCommands", new UserCommandsTab());
    addTab("TurtleControl", new TurtleControlTab(commandController));
    addTab("PenControls", new PenPropertiesTab(turtlePane));
  }

  private void addTab(String resourceKey, TabContent tabContent) {
    String title = LanguageController.getText(resourceKey);
    Tab tab = new Tab(title);
    tab.setContent(tabContent.getContent());
    getTabs().add(tab);
    tabMap.put(title, tabContent);
  }

  @Override
  public void update(Observable observable) {
    if (observable instanceof CommandHistoryModel) {
      updateTabContent(LanguageController.getText("CommandHistory"), observable);
    } else if (observable instanceof VariablesModel) {
      updateTabContent(LanguageController.getText("UserVariables"), observable);
    }
  }

  private void updateTabContent(String title, Observable observable) {
    TabContent tabContent = tabMap.get(title);
    if (tabContent instanceof Observer) {
      ((Observer) tabContent).update(observable);
    }
  }
}
