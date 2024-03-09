package slogo.view.tabs;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import slogo.controller.command.CommandController;
import slogo.controller.config.LanguageController;
import slogo.model.api.data.CommandHistoryModel;
import slogo.observer.Observable;
import slogo.observer.Observer;
import slogo.view.scenes.main.TurtlePane;

/**
 * SideTabPane is the side tab.
 */
public class SideTabPane extends TabPane implements Observer {

  private final CommandController commandController;
  private final Map<String, TabContent> tabContents = new HashMap<>();
  private final TurtlePane turtlePane;

  /**
   * SideTabPane constructor.
   */
  public SideTabPane(CommandController commandController, TurtlePane turtlePane) {
    super();
    this.commandController = commandController;
    this.turtlePane = turtlePane;
    setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    initializeAndPopulateTabs();
  }

  /**
   * Initialize and populate tabs.
   */
  private void initializeAndPopulateTabs() {
    addAndRegisterTab("CommandHistory", new CommandHistoryTab(commandController));
    addAndRegisterTab("HelpDocs", new HelpDocTab());
    addAndRegisterTab("UserCommands", new UserCommandsTab());
    addAndRegisterTab("TurtleControl", new TurtleControlTab(commandController));
    addAndRegisterTab("PenControls", new PenPropertiesTab(turtlePane));
  }

  /**
   * Add and register tab.
   *
   * @param key        is the key.
   * @param tabContent is the tab content.
   */
  private void addAndRegisterTab(String key, TabContent tabContent) {
    String title = LanguageController.getText(key);
    Tab tab = new Tab(title);
    tab.setContent(tabContent.getContent());
    tabContents.put(title, tabContent);
    getTabs().add(tab);
  }

  /**
   * Update function for the observable.
   *
   * @param observable is the observable triggering the update.
   */
  @Override
  public void update(Observable observable) {
    if (observable instanceof CommandHistoryModel) {
      updateCommandHistoryTab(observable);
    }
  }

  /**
   * Update command history tab.
   *
   * @param observable is the observable triggering the update.
   */
  private void updateCommandHistoryTab(Observable observable) {
    CommandHistoryTab commandHistoryTab = (CommandHistoryTab) tabContents.get(
        LanguageController.getText("CommandHistory"));
    if (commandHistoryTab != null) {
      commandHistoryTab.updateContent(((CommandHistoryModel) observable).iterator());
    }
  }

}
