package slogo.model.api.parser.metadata;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.HashMap;
import java.util.Map;

public class CommandMetadataLoader {
    private Map<String, CommandMetadata> commandMetadataMap = new HashMap<>();

    public void loadCommandMetadata(String filePath) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(filePath);
        document.getDocumentElement().normalize();

        NodeList commandList = document.getElementsByTagName("command");
        for (int i = 0; i < commandList.getLength(); i++) {
            Node commandNode = commandList.item(i);
            if (commandNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) commandNode;
                String canonicalName = element.getElementsByTagName("canonicalName").item(0).getTextContent();
                String description = element.getElementsByTagName("description").item(0).getTextContent();
                String example = element.getElementsByTagName("example").item(0).getTextContent();
                String help = element.getElementsByTagName("help").item(0).getTextContent();
                int parametersCount = Integer.parseInt(element.getElementsByTagName("parametersCount").item(0).getTextContent());
                String implementingClass = element.getElementsByTagName("implementingClass").item(0).getTextContent();

                CommandMetadata metadata = new CommandMetadata(canonicalName, description, example, help, parametersCount, implementingClass);
                commandMetadataMap.put(canonicalName.toLowerCase(), metadata);
            }
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