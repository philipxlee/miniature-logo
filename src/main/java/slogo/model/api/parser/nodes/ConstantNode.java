package slogo.model.api.parser.nodes;

import java.util.List;

public class ConstantNode extends ASTNode {
    private final double value;

    public ConstantNode(double value) {
        super();
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public void addChild(ASTNode child) {
        throw new UnsupportedOperationException("Cannot add children to a ConstantNode");
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(); // ConstantNode does not have children
    }
}
