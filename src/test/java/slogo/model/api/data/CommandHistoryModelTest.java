package slogo.model.api.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandHistoryModelTest {

  private static final double TOLERANCE = 0.001;

  @BeforeEach
  void setUp() {
  }

  @Test
  void testHistoryBasic() {
    String command = "fd 50";
    CommandHistoryModel commandHistoryModel = new CommandHistoryModel();
    commandHistoryModel.addCommand(command);
    Iterator<String> commands = commandHistoryModel.iterator();
    assertTrue(commands.hasNext());
    assertEquals(commands.next(), command);
  }

  @Test
  void testHistoryDetailed() {
    String[] commandStrings = new String[]{"fd 50", "cs", "rt 90", "bk 100", "rt 180", "home"};
    CommandHistoryModel commandHistoryModel = new CommandHistoryModel();
    for (String command : commandStrings) {
      commandHistoryModel.addCommand(command);
    }
    Iterator<String> commands = commandHistoryModel.iterator();
    assertTrue(commands.hasNext());
    int i = 0;
    while (commands.hasNext()) {
      assertEquals(commandStrings[i], commands.next());
      i++;
    }
  }
}
