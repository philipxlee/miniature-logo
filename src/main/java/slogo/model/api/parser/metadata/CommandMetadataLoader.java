package slogo.model.api.parser.metadata;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CommandMetadataLoader {
    private Map<String, CommandMetadata> commandMetadataMap = new HashMap<>();
    private static final String COMMANDS_DIRECTORY = "/slogo.commands";

    public void loadAllCommandMetadata() {
        try {
            // Assuming the resources directory is correctly placed under src/main/resources in your Maven project
            Path path = Paths.get(getClass().getResource(COMMANDS_DIRECTORY).toURI());

            // Use Files.walk to iterate over all files in the directory and its subdirectories
            try (Stream<Path> paths = Files.walk(path)) {
                paths.filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".xml")).forEach(this::loadCommandMetadataFromFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCommandMetadataFromFile(Path filePath) {
        try (InputStream stream = Files.newInputStream(filePath)) {
            loadCommandMetadataFromStream(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCommandMetadataFromStream(InputStream stream) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(stream);
            document.getDocumentElement().normalize();

            NodeList commandList = document.getElementsByTagName("command");
            for (int i = 0; i < commandList.getLength(); i++) {
                Node commandNode = commandList.item(i);
                if (commandNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) commandNode;
                    String canonicalName = element.getElementsByTagName("canonicalName").item(0).getTextContent();
                    String alias = element.getElementsByTagName("alias").getLength() > 0 ? element.getElementsByTagName("alias").item(0).getTextContent() : canonicalName;
                    String description = element.getElementsByTagName("description").item(0).getTextContent();
                    String example = element.getElementsByTagName("example").item(0).getTextContent();
                    String help = element.getElementsByTagName("help").item(0).getTextContent();
                    int parametersCount = Integer.parseInt(element.getElementsByTagName("parametersCount").item(0).getTextContent());
                    String implementingClass = element.getElementsByTagName("implementingClass").item(0).getTextContent();

                    CommandMetadata metadata = new CommandMetadata(canonicalName, alias, description, example, help, parametersCount, implementingClass);
                    // Use alias as the key for the command metadata map, defaulting to canonicalName if alias is not provided
                    commandMetadataMap.put(alias.toLowerCase(), metadata);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CommandMetadata getCommandMetadata(String commandName) {
        return commandMetadataMap.get(commandName.toLowerCase());
    }

    // Optional: Method to return all metadata for use in help views, etc.
    public Map<String, CommandMetadata> getAllCommandMetadata() {
        return new HashMap<>(commandMetadataMap);
    }
}