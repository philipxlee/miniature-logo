package slogo.model.api.parser.nodes;

import java.util.List;

public class VariableNode extends ASTNode {
    private final String name;

    public VariableNode(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void addChild(ASTNode child) {
        throw new UnsupportedOperationException("Cannot add children to a VariableNode");
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(); // VariableNode does not have children
    }
}