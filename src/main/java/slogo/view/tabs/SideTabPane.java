package slogo.view.tabs;

import java.util.HashMap;
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
  private final Map<String, TabContent> tabContents = new HashMap<>();
  private final TurtlePane turtlePane;

  public SideTabPane(CommandController commandController, TurtlePane turtlePane) {
    this.commandController = commandController;
    this.turtlePane = turtlePane;
    setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    initializeAndPopulateTabs();
  }

  private void initializeAndPopulateTabs() {
    addAndRegisterTab("CommandHistory", new CommandHistoryTab(commandController));
    addAndRegisterTab("HelpDocs", new HelpDocTab());
    addAndRegisterTab("UserVariables", new UserVariablesTab(commandController));
    addAndRegisterTab("UserCommands", new UserCommandsTab());
    addAndRegisterTab("TurtleControl", new TurtleControlTab(commandController));
    addAndRegisterTab("PenControls", new PenPropertiesTab(turtlePane));
  }

  private void addAndRegisterTab(String key, TabContent tabContent) {
    String title = LanguageController.getText(key);
    Tab tab = new Tab(title);
    tab.setContent(tabContent.getContent());
    tabContents.put(title, tabContent);
    getTabs().add(tab);
  }

  @Override
  public void update(Observable observable) {
    if (observable instanceof CommandHistoryModel) {
      updateCommandHistoryTab(observable);
    } else if (observable instanceof VariablesModel) {
      updateUserVariablesTab(observable);
    }
  }

  private void updateCommandHistoryTab(Observable observable) {
    CommandHistoryTab commandHistoryTab = (CommandHistoryTab) tabContents.get(LanguageController.getText("CommandHistory"));
    if (commandHistoryTab != null) {
      commandHistoryTab.updateContent(((CommandHistoryModel) observable).iterator());
    }
  }

  private void updateUserVariablesTab(Observable observable) {
    UserVariablesTab userVariablesTab = (UserVariablesTab) tabContents.get(LanguageController.getText("UserVariables"));
    if (userVariablesTab != null) {
      userVariablesTab.updateContent(((VariablesModel) observable).getAllVariables());
    }
  }
}
