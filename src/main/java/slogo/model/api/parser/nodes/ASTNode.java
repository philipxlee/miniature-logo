package slogo.model.api.parser.nodes;

import java.util.List;

public abstract class ASTNode {
    protected List<ASTNode> children;

    // Method to add a child node
    public void addChild(ASTNode child) {
        children.add(child);
    }

    // Method to get all children
    public List<ASTNode> getChildren() {
        return children;
    }
}
